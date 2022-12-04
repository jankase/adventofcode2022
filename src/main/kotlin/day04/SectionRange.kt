package day04

internal data class SectionRange(val start: Int, val end: Int) {
    companion object {
        fun valueOf(value: String): SectionRange {
            val items = value.split("-").map { it.toInt() }.toTypedArray()
            if (items.size != 2) error("Expected only 2 values separated by -, not $value")
            return SectionRange(items[0], items[1])
        }
    }

    fun toList(): List<Int> {
        return (start..end).toList()
    }
}
