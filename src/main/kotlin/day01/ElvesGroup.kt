package day01

import split

internal data class ElvesGroup(val elves: List<Elf>) {
    companion object {
        fun valueOf(input: List<String>): ElvesGroup {
            val elfInputs = input.split({ it.isNotBlank() }, true)
            return ElvesGroup(elfInputs.map { Elf.valueOf(it) })
        }
    }

    fun maxCalories(): Int = elves.maxOf { it.calories }
    fun top3CaloriesSum(): Int = elves.map { it.calories }.sorted().takeLast(3).sum()
}
