package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test {

    private val input = readInputFile("05")

    @Test
    fun validateFirstSampleInputs() {
        val boardingPasses = listOf(
            "FBFBBFFRLR",
            "BFFFBBFRRR",
            "FFFBBBFRRR",
            "BBFFBBFRLL"
        )

        assertEquals(357, Day05.solveFirst(listOf(boardingPasses[0])))
        assertEquals(567, Day05.solveFirst(listOf(boardingPasses[1])))
        assertEquals(119, Day05.solveFirst(listOf(boardingPasses[2])))
        assertEquals(820, Day05.solveFirst(listOf(boardingPasses[3])))
        assertEquals(820, Day05.solveFirst(boardingPasses))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(880, Day05.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        // No sample inputs given
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(731, Day05.solveSecond(input))
    }
}
