package day04

internal data class TeamSections(val sections: List<SectionRange>) {
    companion object {
        fun valueOf(value: String): TeamSections {
            return TeamSections(value.split(",").map { SectionRange.valueOf(it) })
        }
    }

    fun sectionsHasFullOverlap(): Boolean {
        val minStart = sections.minOf { it.start }
        val maxEnd = sections.maxOf { it.end }
        return sections.contains(SectionRange(minStart, maxEnd))
    }

    fun sectionsHasOverlap(): Boolean {
        val sectionItems = sections.map { it.toList() }.toTypedArray()
        if (sectionItems.size != 2) error("Expected only 2 items")
        val result = sectionItems[0].intersect(sectionItems[1].toSet())
        return result.isNotEmpty()
    }
}
