package day03

internal data class RucksacksGroup(val rucksacks: List<Rucksack>): ICommonItems {

    companion object {
        fun valueOf(input: List<String>) = RucksacksGroup(input.map { Rucksack.valueOf(it) })
    }
    override fun commonItems(): List<Int> {
        if (rucksacks.size == 1) return rucksacks.first().priorityItems
        val rucksacksItems = rucksacks.map { it.priorityItems }.toTypedArray()
        val result = rucksacksItems[0].toMutableList()
        for (i in 1 until rucksacksItems.size) {
            result.retainAll(rucksacksItems[i])
        }
        return result.distinct()
    }
}
