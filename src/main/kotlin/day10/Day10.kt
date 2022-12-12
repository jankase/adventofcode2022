package day10

import readInput

fun main() {
    fun part1(input: List<String>): Int = SignalProcessor(input.map { Signal.valueOf(it) }).run()

    fun part2(input: List<String>): List<String> = CRTProcessor(input.map { Signal.valueOf(it) }).crtOutput()

    val input = readInput("Day10")
    println(part1(input))
    part2(input).forEach { println(it) }
}
