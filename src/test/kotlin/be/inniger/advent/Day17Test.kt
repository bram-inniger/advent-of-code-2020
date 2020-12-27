package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {

    private val input = readInputFile("17")
    private val initialState = listOf(
        ".#.",
        "..#",
        "###"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(112, Day17.solveFirst(initialState))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(391, Day17.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(848, Day17.solveSecond(initialState))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(2264, Day17.solveSecond(input))
    }
}
