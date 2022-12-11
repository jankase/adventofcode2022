package day09

import readTestResourceFile
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MoveDirectionTests {
    lateinit var lines: List<String>

    @BeforeTest
    fun setup() {
        lines = readTestResourceFile("Day09")
    }

    @Test
    fun testMoveDirectionParsing() {
        val sut = lines.map { MoveDirection.valueOf(it) }
        assertEquals(8, sut.size)
    }
}
