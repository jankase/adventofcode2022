package day14

import Day
import solve
import kotlin.math.max
import kotlin.math.min

data class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {
    companion object {
        fun valueOf(value: String): Coordinate {
            val (x, y) = value.split(",").map(String::toInt)
            return Coordinate(x, y)
        }
    }

    override fun compareTo(other: Coordinate): Int {
        val xCompare = x.compareTo(other.x)
        return if (xCompare == 0) y.compareTo(other.y) else xCompare
    }

    infix fun listTo(that: Coordinate): List<Coordinate> {
        return when {
            x == that.x -> {
                val startY = min(y, that.y)
                val endY = max(y, that.y)
                (startY..endY).map { Coordinate(x, it) }
            }

            y == that.y -> {
                val startX = min(x, that.x)
                val endX = max(x, that.x)
                (startX..endX).map { Coordinate(it, y) }
            }

            else -> error("Invalid edge for progression")
        }
    }
}


data class ObstaclePath(val elements: List<Coordinate>) {
    companion object {
        fun valueOf(value: String): ObstaclePath = ObstaclePath(value.split(" -> ").map(Coordinate::valueOf))
    }

    val obstacles: List<Coordinate>
        get() {
            return when {
                elements.isEmpty() -> listOf()
                elements.size == 1 -> listOf(elements.first())
                else -> {
                    elements.windowed(2).map { (start, end) ->
                        start listTo end
                    }
                        .flatten()
                        .distinct()
                }
            }
        }
}

class Cave(private val obstacles: MutableList<Coordinate>, val useFloor: Boolean = false) {

    val floorY: Int = obstacles.maxOf { it.y } + 2

    fun deliverSandUntilFallToAbyssOrIsNotPossibleToAdd(startCoordinate: Coordinate): Int {
        if (useFloor) {
            val minX = startCoordinate.x - floorY - 1
            val maxX = startCoordinate.x + floorY + 1
            obstacles.addAll(Coordinate(minX, floorY) listTo Coordinate(maxX, floorY))
        }
        var numberOfSandPieces = 0
        var currentSandPosition: Coordinate = startCoordinate
        while (currentSandPosition.y != Int.MAX_VALUE && !obstacles.contains(startCoordinate)) {
            currentSandPosition = startCoordinate
            currentSandPosition = performMoveUntilStableOrFall(currentSandPosition)
            if (currentSandPosition.y != Int.MAX_VALUE) {
                numberOfSandPieces++
                obstacles.add(currentSandPosition)
            }
            if (numberOfSandPieces % 100 == 0) {
                println("Added $numberOfSandPieces pieces")
            }
        }
        return numberOfSandPieces
    }

    private fun performMoveUntilStableOrFall(coordinate: Coordinate): Coordinate {
        if (obstacles.none { it.x == coordinate.x && it.y > coordinate.y })
            return Coordinate(coordinate.x, Int.MAX_VALUE)
        val bellowObstacle = obstacles.filter { it.x == coordinate.x && it.y > coordinate.y }.minOf { it }
        val coordinateAfterFall = bellowObstacle.copy(y = bellowObstacle.y - 1)
        return when {
            obstacles.none { it.x == coordinateAfterFall.x - 1 && it.y == coordinateAfterFall.y + 1 } ->
                performMoveUntilStableOrFall(Coordinate(coordinateAfterFall.x - 1, coordinateAfterFall.y + 1))

            obstacles.none { it.x == coordinateAfterFall.x + 1 && it.y == coordinateAfterFall.y + 1 } ->
                performMoveUntilStableOrFall(Coordinate(coordinateAfterFall.x + 1, coordinateAfterFall.y + 1))

            else -> coordinateAfterFall
        }
    }
}

class Day14 : Day(14, 2022, "Regolith Reservoir") {
    override fun part1(): Int {
        val cave = Cave(
            input.asSequence()
                .map(ObstaclePath::valueOf)
                .map(ObstaclePath::obstacles)
                .flatten()
                .distinct().sorted().toMutableList()
        )
        return cave.deliverSandUntilFallToAbyssOrIsNotPossibleToAdd(Coordinate(500, 0))
    }

    override fun part2(): Int {
        val cave = Cave(
            input.asSequence()
                .map(ObstaclePath::valueOf)
                .map(ObstaclePath::obstacles)
                .flatten()
                .distinct().sorted().toMutableList(),
            true
        )
        return cave.deliverSandUntilFallToAbyssOrIsNotPossibleToAdd(Coordinate(500, 0))
    }
}

fun main() {
    solve<Day14> {
        day14demo part1 24 part2 93
    }
}

private val day14demo = """
    498,4 -> 498,6 -> 496,6
    503,4 -> 502,4 -> 502,9 -> 494,9
""".trimIndent()
