package day11

import Day
import extractAllIntegers
import extractAllLongs
import lcm
import product
import solve

typealias WorryLevel = Long
fun String.toWorryLevel(): WorryLevel? = toLongOrNull()
infix fun WorryLevel.divisibleBy(divisor: Int) = this % divisor == 0L

class Day11 : Day(11, 2022, "Monkey in the Middle") {

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

    override fun part1(): Long {
        val inspections = letMonkeysPlay(20, ::playWithItemWorryLevel)
        return inspections.sortedDescending().take(2).product()
    }

    override fun part2(): Long {
        val inspections = letMonkeysPlay(10000, ::playWithItemWorryLevelWithoutLimits)
        return inspections.sortedDescending().take(2).product()
    }
}

fun main() {
    solve<Day11> {
        day11DemoInput part1 10605 part2 2713310158
    }
}

private val day11DemoInput = """
Monkey 0:
  Starting items: 79, 98
  Operation: new = old * 19
  Test: divisible by 23
    If true: throw to monkey 2
    If false: throw to monkey 3

Monkey 1:
  Starting items: 54, 65, 75, 74
  Operation: new = old + 6
  Test: divisible by 19
    If true: throw to monkey 2
    If false: throw to monkey 0

Monkey 2:
  Starting items: 79, 60, 97
  Operation: new = old * old
  Test: divisible by 13
    If true: throw to monkey 1
    If false: throw to monkey 3

Monkey 3:
  Starting items: 74
  Operation: new = old + 3
  Test: divisible by 17
    If true: throw to monkey 0
    If false: throw to monkey 1
""".trimIndent()
