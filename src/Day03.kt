fun main() {

    fun part1(input: List<String>): Int {
        val rucksacks = input.map { Rucksack.valueOf(it) }
        val commonItems = rucksacks.map { it.commonItems() }.flatten()
        val commonItemsValues = commonItems.map { it.priorityValue }
        return commonItemsValues.sum()
    }

    fun part2(input: List<String>): Int {
        val elvesGroup = ElvesGroup.createGroups(input, 3)
        val prioritiesItems = elvesGroup.map { it.commonItems() }.flatten()
        val prioritiesValues = prioritiesItems.map { it.priorityValue }
        return prioritiesValues.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

private val Char.priorityValue: Int
    get(): Int = when(this) {
        in 'a'..'z' -> this - 'a' + 1
        in 'A'..'Z' -> this - 'A' + 27
        else -> error("wrong character")
    }

private data class ElvesGroup(val rucksacks: List<Rucksack>) {
    companion object {
        fun createGroups(input: List<String>, groupSize: Int): List<ElvesGroup> {
            val groupedInput = input.chunked(groupSize)
            return groupedInput.map { createGroup(it) }
        }

        fun createGroup(input: List<String>): ElvesGroup {
            return ElvesGroup(input.map { Rucksack.valueOf(it) })
        }
    }

    fun commonItems(): List<Char> {
        val itemsInSacks = rucksacks.map { it.getAllItems() }.toTypedArray()
        if (itemsInSacks.size == 1) return itemsInSacks.first()
        val result = itemsInSacks[0].toMutableList()
        for (i in 1 until itemsInSacks.size) {
            result.retainAll(itemsInSacks[i])
        }
        return result
    }
}

private data class Compartment(val content: List<Char>)

private data class Rucksack(val firstCompartment: Compartment, val secondCompartment: Compartment) {
    companion object {
        fun valueOf(value: String): Rucksack {
            val chars = value.toList().chunked(value.length / 2)
            return Rucksack(Compartment(chars.first()), Compartment(chars.last()))
        }
    }

    fun getAllItems(): List<Char> {
        return firstCompartment.content.union(secondCompartment.content).toList()
    }

    fun commonItems(): List<Char> {
        val result = firstCompartment.content.toMutableList()
        result.retainAll(secondCompartment.content)
        return result.distinct()
    }
}
