package day06

import readInput

fun main() {
    fun part1(input: List<String>): Int = SignalDetector(input.first()).findPacketStartMarker()

    fun part2(input: List<String>): Int = SignalDetector(input.first()).findMessageStartMarker()

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
