package day01

import readInput

fun main() {
    fun part1(input: List<String>): Int = ElvesGroup.valueOf(input).maxCalories()

    fun part2(input: List<String>): Int = ElvesGroup.valueOf(input).top3CaloriesSum()

    val input = readInput("Day01")
    println("Max calories: ${part1(input)}")
    println("Top 3 calories: ${part2(input)}")
}
