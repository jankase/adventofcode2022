package day10

import readTestResourceFile
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SignalProcessorTests {
    private lateinit var signals: List<Signal>

    @BeforeTest
    fun setup() {
        signals = readTestResourceFile("Day10").map { Signal.valueOf(it) }
    }

    @Test
    fun testBasicProcessor() {
        val sut = SignalProcessor(signals)
        assertEquals(13140, sut.run())
    }
}
