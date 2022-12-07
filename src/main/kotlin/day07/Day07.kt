package day07

import readInput

fun main() {
    fun part1(input: List<String>): Int =
        FileSystem(FileSystemInstruction.valueOf(input))
            .findRecursive { it is Directory && it.size <= 100000 }
            .sumOf { it.size }

    fun part2(input: List<String>): Int =
        FileSystem(FileSystemInstruction.valueOf(input)).findDirectoryToFreeUpSpace().size

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
