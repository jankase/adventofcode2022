package day04

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class SectionRangeTest {
    @Test
    fun testInitWithCorrectParams() {
        val sut = SectionRange.valueOf("1-2")
        assertEquals(1, sut.start)
        assertEquals(2, sut.end)
    }

    @Test
    fun testWrongInputParams() {
        assertFails {
            SectionRange.valueOf("1-2-3")
        }
    }
}
