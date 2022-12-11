package day07

import readTestResourceFile
import kotlin.test.Test
import kotlin.test.assertEquals

class FileSystemInstructionTest {

    @Test
    fun testParseInput() {
        val sut = FileSystemInstruction.valueOf(readTestResourceFile("Day07"))
        assertEquals(23, sut.size)
    }
}
