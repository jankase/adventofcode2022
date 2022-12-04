package day04

internal data class Teams(val sections: List<TeamSections>) {
    companion object {
        fun valueOf(value: List<String>): Teams {
            return Teams(value.map { TeamSections.valueOf(it) })
        }
    }

    fun numberOfTeamSectionsWithFullOverlap(): Int = sections.filter { it.sectionsHasFullOverlap() }.size

    fun numberOfTeamSectionsWithOverlap(): Int = sections.filter { it.sectionsHasOverlap() }.size
}
