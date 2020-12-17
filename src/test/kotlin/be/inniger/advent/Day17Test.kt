package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day17Test {

    private val problem = Day17()
    private val input = readInputFile("17")
    private val initialState = listOf(
        ".#.",
        "..#",
        "###"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(112, problem.solveFirst(initialState))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(391, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(848, problem.solveSecond(initialState))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(2264, problem.solveSecond(input))
    }
}
