package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test {

    private val problem = Day05()
    private val input = readInputFile("05")

    @Test
    fun validateFirstSampleInputs() {
        val boardingPasses = listOf(
            "FBFBBFFRLR",
            "BFFFBBFRRR",
            "FFFBBBFRRR",
            "BBFFBBFRLL"
        )

        assertEquals(357, problem.solveFirst(listOf(boardingPasses[0])))
        assertEquals(567, problem.solveFirst(listOf(boardingPasses[1])))
        assertEquals(119, problem.solveFirst(listOf(boardingPasses[2])))
        assertEquals(820, problem.solveFirst(listOf(boardingPasses[3])))
        assertEquals(820, problem.solveFirst(boardingPasses))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(880, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        // No sample inputs given
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(731, problem.solveSecond(input))
    }
}
