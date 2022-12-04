package day02

import readInput

fun main() {
    fun part1(input: List<String>): Int = Game(input.map { Round.fromOpponentAndYourPlay(it) }).totalScore()

    fun part2(input: List<String>): Int = Game(input.map { Round.fromOpponentPlayAndDesiredResult(it) }).totalScore()

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
