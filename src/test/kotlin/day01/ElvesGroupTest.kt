package day01

import org.junit.Before
import org.junit.Test
import readTestResourceFile
import kotlin.test.assertEquals

internal class ElvesGroupTest {

    private lateinit var elvesGroup: ElvesGroup
    @Before
    fun setup() {
        elvesGroup = ElvesGroup.valueOf(readTestResourceFile("Day01"))
    }

    @Test
    fun testMaxCalories() {
        assertEquals(24000, elvesGroup.maxCalories())
    }

    @Test
    fun testTop3Calories() {
        assertEquals(45000, elvesGroup.top3CaloriesSum())
    }
}
