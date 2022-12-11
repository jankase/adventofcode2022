package day03

import readTestResourceFile
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ContainerTests {
    private lateinit var lines: List<String>

    @BeforeTest
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
