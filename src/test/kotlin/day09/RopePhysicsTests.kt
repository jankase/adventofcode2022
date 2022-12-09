package day09

import org.junit.Before
import org.junit.Test
import readTestResourceFile
import kotlin.test.assertEquals

class RopePhysicsTests {

    private lateinit var ropePhysics: RopePhysics
    @Before
    fun setup() {
        ropePhysics = RopePhysics.valueOf(readTestResourceFile("Day09"))
    }
    @Test
    fun testMove() {
        assertEquals(13, ropePhysics.performMoveInstructions().size)
    }
}
