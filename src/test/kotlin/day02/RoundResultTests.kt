package day02

import kotlin.test.Test
import kotlin.test.assertEquals

class RoundResultTests {
    @Test
    fun testRoundResultInit() {
        assertEquals(RoundResult.LOST, RoundResult.valueOf("X"))
        assertEquals(RoundResult.DRAW, RoundResult.valueOf("Y"))
        assertEquals(RoundResult.WIN, RoundResult.valueOf("Z"))
    }
}
