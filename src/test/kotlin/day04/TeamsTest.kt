package day04

import readTestResourceFile
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class TeamsTest {
    private lateinit var teams: Teams

    @BeforeTest
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
