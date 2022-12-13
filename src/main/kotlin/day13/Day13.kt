package day13

import Day
import safeTimes
import solve

typealias Signal = List<Any>

class Day13 : Day(13, 2022, "Distress Signal") {
    override fun part1(): Int {
        val signalGroups = inputAsGroups.map { (firstSetOfSignals, secondSetOfSignals) ->
            Pair(firstSetOfSignals.toSignal(), secondSetOfSignals.toSignal())
        }
        return signalGroups.mapIndexed { index, (left, right) ->
            if (left.compareTo(right) <= 0) index + 1 else 0
        }.sum()
    }

    override fun part2(): Int {
        val allSignals = input.filter(onlyDataLines).map { it.toSignal() }.toMutableList()
        val signal2 = listOf(listOf<Any>(2))
        val signal6 = listOf(listOf<Any>(6))
        allSignals.add(signal2)
        allSignals.add(signal6)
        allSignals.sortWith { o1, o2 -> o1.compareTo(o2) }
        val indexOf2 = allSignals.indexOf(signal2) + 1
        val indexOf6 = allSignals.indexOf(signal6) + 1
        return indexOf2 safeTimes indexOf6
    }

    private fun String.toSignal(): Signal {
        var workingString = removeSurrounding("[", "]")
        val stack = mutableListOf<MutableList<Any>>(mutableListOf())
        val separators = listOf('[', ']', ',')
        while (workingString.isNotEmpty()) {
            val (newWorkingString, segment, separator) = workingString.dropTillSeparators(separators)
            workingString = newWorkingString
            if (segment.toIntOrNull() != null) stack.last().add(segment.toInt())
            when (separator) {
                "[" -> stack.add(mutableListOf())
                "]" -> {
                    val closedList = stack.removeLast()
                    stack.last().add(closedList)
                }
            }
        }
        return stack.single()
    }

    private fun String.dropTillSeparators(separators: List<Char>): Triple<String, String, String> {
        val newSegment = takeWhile { !separators.contains(it) }
        var newResult = removePrefix(newSegment)
        val separator = newResult.take(1)
        newResult = newResult.removePrefix(separator)
        return Triple(newResult, newSegment, separator)
    }

    @Suppress("KotlinConstantConditions", "UNCHECKED_CAST")
    private fun Signal.compareTo(other: Signal): Int {
        val leftIterator = this.iterator()
        val rightIterator = other.iterator()
        var nextLeft = leftIterator.nextOrNull()
        var nextRight = rightIterator.nextOrNull()
        while (nextRight != null || nextLeft != null) {
            val result = when {
                nextRight == null && nextLeft != null -> 1
                nextRight != null && nextLeft == null -> -1
                nextRight is Int && nextLeft is Int -> nextLeft.compareTo(nextRight)
                nextLeft is Int && nextRight !is Int -> listOf<Any>(nextLeft).compareTo(nextRight as Signal)
                nextLeft !is Int && nextRight is Int -> (nextLeft as Signal).compareTo(listOf(nextRight))
                else -> (nextLeft as Signal).compareTo(nextRight as Signal)
            }
            if (result != 0) return result
            nextLeft = leftIterator.nextOrNull()
            nextRight = rightIterator.nextOrNull()
        }
        return 0
    }

    private fun <T> Iterator<T>.nextOrNull(): T? =
        if (hasNext()) next() else null
}

fun main() {
    solve<Day13> {
        day13Demo part1 13 part2 140
    }
}

val day13Demo = """
    [1,1,3,1,1]
    [1,1,5,1,1]

    [[1],[2,3,4]]
    [[1],4]

    [9]
    [[8,7,6]]

    [[4,4],4,4]
    [[4,4],4,4,4]

    [7,7,7,7]
    [7,7,7]

    []
    [3]

    [[[]]]
    [[]]

    [1,[2,[3,[4,[5,6,7]]]],8,9]
    [1,[2,[3,[4,[5,6,0]]]],8,9]
""".trimIndent()
