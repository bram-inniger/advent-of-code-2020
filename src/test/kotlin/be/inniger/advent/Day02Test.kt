package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {

    private val problem = Day02()
    private val input = readInputFile("02")
    private val passwords = listOf(
        "1-3 a: abcde",
        "1-3 b: cdefg",
        "2-9 c: ccccccccc"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(2, problem.solveFirst(passwords))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(600, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(1, problem.solveSecond(passwords))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(245, problem.solveSecond(input))
    }
}
