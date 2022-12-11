package day02

import kotlin.test.Test
import kotlin.test.assertEquals

class PlayTest {
    @Test
    fun testRockInit() {
        assertEquals(Play.ROCK, Play.valueOf("A"))
        assertEquals(Play.ROCK, Play.valueOf("X"))
    }
    @Test
    fun testPaperInit() {
        assertEquals(Play.PAPER, Play.valueOf("B"))
        assertEquals(Play.PAPER, Play.valueOf("Y"))
    }
    @Test
    fun testScissorsInit() {
        assertEquals(Play.SCISSORS, Play.valueOf("C"))
        assertEquals(Play.SCISSORS, Play.valueOf("Z"))
    }
}
