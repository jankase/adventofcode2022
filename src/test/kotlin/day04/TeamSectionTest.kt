package day04

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TeamSectionTest {
    @Test
    fun testFullOverlap() {
        assertTrue(TeamSections.valueOf("1-4,2-3").sectionsHasFullOverlap())
        assertTrue(TeamSections.valueOf("2-3,1-4").sectionsHasFullOverlap())
        assertTrue(TeamSections.valueOf("2-4,1-4").sectionsHasFullOverlap())
        assertFalse(TeamSections.valueOf("2-3,3-4").sectionsHasFullOverlap())
        assertFalse(TeamSections.valueOf("3-4,2-3").sectionsHasFullOverlap())
    }
}
