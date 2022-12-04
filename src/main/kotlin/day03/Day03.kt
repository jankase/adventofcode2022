package day03

import readInput

fun main() {

    fun part1(input: List<String>): Int = Container.rucksacks(input).calculatePriority()

    fun part2(input: List<String>): Int = Container.rucksacksGroups(input, 3).calculatePriority()

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
