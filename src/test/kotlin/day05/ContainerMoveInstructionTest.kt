package day05

import org.junit.Test
import kotlin.test.assertEquals

class ContainerMoveInstructionTest {
    @Test
    fun testParsingLoadInstruction() {
        assertEquals(ContainerMoveInstruction(1, 2, 1), ContainerMoveInstruction.valueOf("move 1 from 2 to 1"))
    }
}
