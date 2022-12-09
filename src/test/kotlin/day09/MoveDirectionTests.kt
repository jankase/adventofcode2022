package day09

import org.junit.Before
import org.junit.Test
import readTestResourceFile
import kotlin.test.assertEquals

class MoveDirectionTests {
    lateinit var lines: List<String>

    @Before
    fun setup() {
        lines = readTestResourceFile("Day09")
    }

    @Test
    fun testMoveDirectionParsing() {
        val sut = lines.map { MoveDirection.valueOf(it) }
        assertEquals(8, sut.size)
    }
}
