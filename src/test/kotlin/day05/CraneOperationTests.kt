package day05

import org.junit.Test
import readTestResourceFile
import kotlin.test.assertEquals

class CraneOperationTests {

    private var lines = readTestResourceFile("Day05")

    @Test
    fun testOperationsCrane9000() {
        val craneOperation = CraneOperation.valueOf(lines, Crane9000())
        craneOperation.performInstructions()
        assertEquals("CMZ", craneOperation.ship.topContainers())
    }

    @Test
    fun testOperationsCrane9001() {
        val craneOperation = CraneOperation.valueOf(lines, Crane9001())
        craneOperation.performInstructions()
        assertEquals("MCD", craneOperation.ship.topContainers())
    }
}
