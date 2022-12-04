package day01

import org.junit.Test
import kotlin.test.assertEquals

class ElfTest {
    @Test
    fun testInitWithListOfString() {
        assertEquals(300, Elf.valueOf(listOf("100", "100", "100")).calories)
    }
}
