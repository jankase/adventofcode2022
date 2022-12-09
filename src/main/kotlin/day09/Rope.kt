package day09

internal class Rope(numberOfSegments: Int) {
    private val knots: List<Position>

    init {
        knots = List(numberOfSegments) { _ -> Position(0, 0) }
    }

    fun move(moveDirection: MoveDirection): Set<Position> {
        var lastPosition: Position? = null
        var result = setOf<Position>()
        knots.forEach {
            if (lastPosition == null) {
                move(moveDirection, it)
                lastPosition = it.copy()
            } else {
                result = move(it, lastPosition ?: return@forEach)
                lastPosition = it.copy()
            }
        }
        if (knots.size > 2) println("New rope shape: $knots")
        return result
    }

    private fun move(direction: MoveDirection, from: Position) {
        when (direction) {
            is MoveDirection.RIGHT -> from.x += direction.distance
            is MoveDirection.LEFT -> from.x -= direction.distance
            is MoveDirection.UP -> from.y += direction.distance
            is MoveDirection.DOWN -> from.y -= direction.distance
        }
    }

    private fun move(nextKnot: Position, closeTo: Position): Set<Position> {
        val result = mutableSetOf<Position>()
        while (!closeTo.closeTo(nextKnot)) {
            when {
                closeTo.y == nextKnot.y -> if (closeTo.x > nextKnot.x) nextKnot.x++ else nextKnot.x--
                closeTo.x == nextKnot.x -> if (closeTo.y > nextKnot.y) nextKnot.y++ else nextKnot.y--
                else -> {
                    nextKnot.x += if (closeTo.x > nextKnot.x) 1 else -1
                    nextKnot.y += if (closeTo.y > nextKnot.y) 1 else -1
                }
            }
            result.add(nextKnot.copy())
        }
        return result
    }
}
