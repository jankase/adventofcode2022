package day02

import org.junit.Before
import org.junit.Test
import readTestResourceFile
import kotlin.test.assertEquals

class GameTests {

    private lateinit var lines: List<String>

    @Before
    fun setup() {
        lines = readTestResourceFile("Day02")
    }

    @Test
    fun testTotalGameScoreTwoPlays() {
        val game = Game(lines.map { Round.fromOpponentAndYourPlay(it) })
        assertEquals(15, game.totalScore())
    }

    @Test
    fun testTotalGameScoreOpponentPlayAndDesiredResult() {
        val game = Game(lines.map { Round.fromOpponentPlayAndDesiredResult(it) })
        assertEquals(12, game.totalScore())
    }
}
