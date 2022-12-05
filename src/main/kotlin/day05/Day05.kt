package day05

import readInput

fun main() {
    fun part1(input: List<String>): String {
        val craneOperation = CraneOperation.valueOf(input, Crane9000())
        craneOperation.performInstructions()
        return craneOperation.ship.topContainers()
    }

    fun part2(input: List<String>): String {
        val craneOperation = CraneOperation.valueOf(input, Crane9001())
        craneOperation.performInstructions()
        return craneOperation.ship.topContainers()
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
