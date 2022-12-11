package day01

import kotlin.test.Test
import kotlin.test.assertEquals

internal class ElfTest {
    @Test
    fun testInitWithListOfString() {
        assertEquals(300, Elf.valueOf(listOf("100", "100", "100")).calories)
    }
}
