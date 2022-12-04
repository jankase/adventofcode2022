package day04

import readInput

fun main() {
    fun part1(input: List<String>): Int = Teams.valueOf(input).numberOfTeamSectionsWithFullOverlap()

    fun part2(input: List<String>): Int = Teams.valueOf(input).numberOfTeamSectionsWithOverlap()

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
