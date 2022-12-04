package day03

internal data class Rucksack(val priorityItems: List<Int>) : ICommonItems {
    companion object {
        fun valueOf(value: String): Rucksack = Rucksack(value.toList().map { it.priorityValue })
    }

    override fun commonItems(): List<Int> {
        val chunked = priorityItems.chunked(priorityItems.size / 2).toTypedArray()
        if (chunked.size != 2) error("expected only two pieces")
        val result = chunked[0].toMutableList()
        result.retainAll(chunked[1].toSet())
        return result.distinct()
    }
}

private val Char.priorityValue: Int
    get(): Int = when (this) {
        in 'a'..'z' -> this - 'a' + 1
        in 'A'..'Z' -> this - 'A' + 27
        else -> error("wrong character")
    }
