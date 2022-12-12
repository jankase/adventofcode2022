package day10

import readTestResourceFile
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SignalTests {
    private lateinit var lines: List<String>

    @BeforeTest
    fun setup() {
        lines = readTestResourceFile("Day10")
    }

    @Test
    fun testParsing() {
        val sut = lines.map { Signal.valueOf(it) }
        assertEquals(146, sut.size)
    }
}
