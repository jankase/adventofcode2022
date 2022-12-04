package day03

import org.junit.Before
import org.junit.Test
import readTestResourceFile
import kotlin.test.assertEquals

class ContainerTests {
    private lateinit var lines: List<String>

    @Before
    fun setup() {
        lines = readTestResourceFile("Day03")
    }

    @Test
    fun testRucksackWithTwoCompartments() {
        assertEquals(157, Container.rucksacks(lines).calculatePriority())
    }

    @Test
    fun testGroupOfRucksacks() {
        assertEquals(70, Container.rucksacksGroups(lines, 3).calculatePriority())
    }
}
