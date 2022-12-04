package day03

internal data class Container(val items: List<ICommonItems>) {
    companion object {
        fun rucksacks(input: List<String>): Container = Container(input.map { Rucksack.valueOf(it) })

        fun rucksacksGroups(input: List<String>, chunkSize: Int): Container =
            Container(input.chunked(chunkSize).map { RucksacksGroup.valueOf(it) })
    }

    fun calculatePriority() = items.flatMap { it.commonItems() }.sum()
}
