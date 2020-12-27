package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {

    private val input = readInputFile("15")[0].split(",").map { it.toInt() }

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(436, Day15.solveFirst(listOf(0, 3, 6)))
        assertEquals(1, Day15.solveFirst(listOf(1, 3, 2)))
        assertEquals(10, Day15.solveFirst(listOf(2, 1, 3)))
        assertEquals(27, Day15.solveFirst(listOf(1, 2, 3)))
        assertEquals(78, Day15.solveFirst(listOf(2, 3, 1)))
        assertEquals(438, Day15.solveFirst(listOf(3, 2, 1)))
        assertEquals(1836, Day15.solveFirst(listOf(3, 1, 2)))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(257, Day15.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(175_594, Day15.solveSecond(listOf(0, 3, 6)))
        assertEquals(2_578, Day15.solveSecond(listOf(1, 3, 2)))
        assertEquals(3_544_142, Day15.solveSecond(listOf(2, 1, 3)))
        assertEquals(261_214, Day15.solveSecond(listOf(1, 2, 3)))
        assertEquals(6_895_259, Day15.solveSecond(listOf(2, 3, 1)))
        assertEquals(18, Day15.solveSecond(listOf(3, 2, 1)))
        assertEquals(362, Day15.solveSecond(listOf(3, 1, 2)))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(8_546_398, Day15.solveSecond(input))
    }
}
