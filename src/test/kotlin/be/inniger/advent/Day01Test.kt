package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    private val input = readInputFile("01").map(String::toInt)

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(514579, Day01.solveFirst(listOf(1721, 979, 366, 299, 675, 1456)))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(270144, Day01.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(241861950, Day01.solveSecond(listOf(1721, 979, 366, 299, 675, 1456)))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(261342720, Day01.solveSecond(input))
    }
}
