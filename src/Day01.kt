import kotlin.math.min

fun main() {
  fun calories(input: List<String>): List<Int> {
    val elfs = input.split({ it.isNotBlank() }, true)
    return elfs.map { stringCalories -> stringCalories.sumOf { it.toInt() } }
  }

  fun part1(input: List<String>): Int {
    val calories = calories(input)
    return calories.max()
  }

  fun part2(input: List<String>): Int {
    val calories = calories(input)
    val top3Calories = calories.sorted().takeLast(3)
    return top3Calories.sum()
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day01_test")
  check(part1(testInput) == 24000)
  check(part2(testInput) == 45000)

  val input = readInput("Day01")
  println(part1(input))
  println(part2(input))
}


fun <T> Iterable<T>.split(predicate: (T) -> Boolean, removeSeparator: Boolean): List<List<T>> {
  var remaining = this.toList()
  val result = mutableListOf<List<T>>()
  while (remaining.isNotEmpty()) {
    val newGroup = remaining.takeWhile(predicate)
    result.add(newGroup)
    val fromIndex = newGroup.size + if (removeSeparator) 1 else 0
    remaining = remaining.subList(min(fromIndex, remaining.size), remaining.size)
  }
  return result
}
