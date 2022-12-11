package day08

import readTestResourceFile
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ForestTests {

    private lateinit var forest: Forest

    @BeforeTest
    fun setup() {
        forest = Forest.valueOf(readTestResourceFile("Day08"))
    }
    @Test
    fun testNumberTreesVisibleFromEdges() {
        assertEquals(21, forest.numberOfVisibleTrees())
    }

    @Test
    fun testScenicScore() {
        forest.highestScenicScore()
        assertEquals(4, forest.trees.filter { it.row == 1 && it.column == 2 }[0].scenicScore)
        assertEquals(8, forest.trees.filter { it.row == 3 && it.column == 2 }[0].scenicScore)
    }
}
