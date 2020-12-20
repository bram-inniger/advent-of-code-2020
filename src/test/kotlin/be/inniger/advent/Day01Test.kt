package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    private val problem = Day01()
    private val input = readInputFile("01").map(String::toInt)

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(514579, problem.solveFirst(listOf(1721, 979, 366, 299, 675, 1456)))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(270144, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(241861950, problem.solveSecond(listOf(1721, 979, 366, 299, 675, 1456)))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(261342720, problem.solveSecond(input))
    }
}
