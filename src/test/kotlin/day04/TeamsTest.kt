package day04

import org.junit.Before
import org.junit.Test
import readTestResourceFile
import kotlin.test.assertEquals

class TeamsTest {
    private lateinit var teams: Teams

    @Before
    fun setup() {
        teams = Teams.valueOf(readTestResourceFile("Day04"))
    }

    @Test
    fun testTeamsWithFullOverlap() {
        assertEquals(2, teams.numberOfTeamSectionsWithFullOverlap())
    }

    @Test
    fun testTeamsWithOverlaps() {
        assertEquals(4, teams.numberOfTeamSectionsWithOverlap())
    }
}
