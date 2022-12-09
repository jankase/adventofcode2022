package day09

import org.junit.Test
import readTestResourceFile
import kotlin.test.assertEquals

class RopePhysicsTests {
    @Test
    fun testMove() {
        val ropePhysics = RopePhysics.valueOf(readTestResourceFile("Day09"))
        assertEquals(13, ropePhysics.performMoveInstructions(2).size)
    }

    @Test
    fun testMoveFor10Knots() {
        val ropePhysics = RopePhysics.valueOf(readTestResourceFile("Day09_2"))
        assertEquals(36, ropePhysics.performMoveInstructions(10).size)
    }
}
