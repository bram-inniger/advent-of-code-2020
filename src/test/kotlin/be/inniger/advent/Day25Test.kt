package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Test {

    private val input = readInputFile("25").map { it.toLong() }

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(14_897_079, Day25.solveFirst(5_764_801, 17_807_724))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(7_032_853, Day25.solveFirst(input[0], input[1]))
    }

    @Test
    fun validateSecondSampleInputs() {
        // No part 2 of the puzzle
    }

    @Test
    fun validateSecondSolution() {
        // No part 2 of the puzzle
    }
}
