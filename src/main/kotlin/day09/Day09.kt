package day09

import readInput

fun main() {
    fun part1(input: List<String>): Int = RopePhysics.valueOf(input).performMoveInstructions().size

    fun part2(input: List<String>): Int = input.size

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
