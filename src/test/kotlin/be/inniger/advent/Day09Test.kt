package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day09Test {

    private val input = readInputFile("09").map { it.toLong() }
    private val xmas = listOf<Long>(
        35,
        20,
        15,
        25,
        47,
        40,
        62,
        55,
        65,
        95,
        102,
        117,
        150,
        182,
        127,
        219,
        299,
        277,
        309,
        576
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(127, Day09.solveFirst(xmas, 5))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(731031916, Day09.solveFirst(input, 25))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(62, Day09.solveSecond(xmas, 5))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(93396727, Day09.solveSecond(input, 25))
    }
}
