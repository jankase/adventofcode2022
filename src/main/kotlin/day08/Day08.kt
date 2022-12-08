package day08

import readInput

fun main() {
    fun part1(input: List<String>): Int = Forest.valueOf(input).numberOfVisibleTrees()

    fun part2(input: List<String>): Int = Forest.valueOf(input).highestScenicScore()

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
