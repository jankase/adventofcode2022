package day11

import Day
import extractAllIntegers
import extractAllLongs
import lcm
import product

typealias WorryLevel = Long
fun String.toWorryLevel(): WorryLevel? = toLongOrNull()
infix fun WorryLevel.divisibleBy(divisor: Int) = this % divisor == 0L

class Day11 : Day(2022, 11) {

    private val monkeys = inputAsGroups.mapIndexed(::createMonkeyIndexed)
    private val startItems: List<List<WorryLevel>> = inputAsGroups.map { it[1].extractAllLongs() }

    private val commonModulus = monkeys.map { it.testDivisor }.lcm().toInt()

    private fun playWithItemWorryLevel(monkey: Monkey, worryLevel: WorryLevel): MonkeyPlayResult {
        val newWorryLevel = monkey.inspectionOperation(worryLevel) / 3
        val throwTo = if (newWorryLevel divisibleBy monkey.testDivisor) monkey.ifTrueDestinationID else monkey.ifFalseDestinationID
        return MonkeyPlayResult(newWorryLevel, throwTo)
    }

    private fun playWithItemWorryLevelWithoutLimits(monkey: Monkey, worryLevel: WorryLevel): MonkeyPlayResult {
        val newWorryLevel = monkey.inspectionOperation(worryLevel) % commonModulus
        val throwTo = if (newWorryLevel divisibleBy monkey.testDivisor) monkey.ifTrueDestinationID else monkey.ifFalseDestinationID
        return MonkeyPlayResult(newWorryLevel, throwTo)
    }

    private fun letMonkeysPlay(rounds: Int, playOperation: (Monkey, WorryLevel) -> MonkeyPlayResult): IntArray {
        val inspections = IntArray(monkeys.size)
        val currentState = startItems.map { it.toMutableList() }

        repeat(rounds) {
            monkeys.zip(currentState).forEach { (monkey, currentlyHolds) ->
                currentlyHolds.forEach {
                    val (newLevel, toMonkey) = playOperation(monkey, it)
                    currentState[toMonkey] += newLevel
                }
                inspections[monkey.id] += currentlyHolds.size
                currentlyHolds.clear()
            }
        }
        return inspections
    }

    private fun createMonkeyIndexed(index: Int, description: List<String>): Monkey {
        val (operation, test, ifTrue, ifFalse) = description.drop(2)
        return Monkey(
            index,
            createOperation(operation.substringAfter("new = ")),
            test.extractAllIntegers().single(),
            ifTrue.extractAllIntegers().single(),
            ifFalse.extractAllIntegers().single(),
        )
    }

    private fun createOperation(input: String): (WorryLevel) -> WorryLevel {
        val (_, operator, operand) = input.split(" ")
        return when (operator) {
            "+" -> { old -> old + (operand.toWorryLevel() ?: old) }
            "*" -> { old -> old * (operand.toWorryLevel() ?: old) }
            else -> error(input)
        }
    }

    fun part1(): Long {
        val inspections = letMonkeysPlay(20, ::playWithItemWorryLevel)
        return inspections.sortedDescending().take(2).product()
    }

    fun part2(): Long {
        val inspections = letMonkeysPlay(10000, ::playWithItemWorryLevelWithoutLimits)
        return inspections.sortedDescending().take(2).product()
    }
}

fun main() {
    val day = Day11()
    println(day.part1())
    println(day.part2())
}
