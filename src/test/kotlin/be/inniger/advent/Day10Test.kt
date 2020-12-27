package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    private val problem = Day10
    private val input = readInputFile("10").map { it.toInt() }
    private val shorterExample = listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)
    private val longerExample = listOf(
        28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19,
        38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(35, problem.solveFirst(shorterExample))
        assertEquals(220, problem.solveFirst(longerExample))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(2201, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(8, problem.solveSecond(shorterExample))
        assertEquals(19_208, problem.solveSecond(longerExample))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(169_255_295_254_528, problem.solveSecond(input))
    }
}
