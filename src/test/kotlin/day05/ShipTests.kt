package day05

import readTestResourceFile
import split
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ShipTests {

    private lateinit var ship: Ship
    @BeforeTest
    fun setup() {
        val input = readTestResourceFile("Day05").split({ it.isNotBlank() }, true).first()
        ship = Ship.parseLoad(input)
    }

    @Test
    fun testLoadParsing() {
        assertEquals(Ship(mutableMapOf(1 to listOf('Z', 'N'), 2 to listOf('M', 'C', 'D'), 3 to listOf('P'))), ship)
    }

    @Test
    fun testTopContainers() {
        assertEquals("NDP", ship.topContainers())
    }
}
