package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {

    private val input = readInputFile("02")
    private val passwords = listOf(
        "1-3 a: abcde",
        "1-3 b: cdefg",
        "2-9 c: ccccccccc"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(2, Day02.solveFirst(passwords))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(600, Day02.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(1, Day02.solveSecond(passwords))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(245, Day02.solveSecond(input))
    }
}
