package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Test {

    private val problem = Day25()
    private val input = readInputFile("25").map { it.toInt() }

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(14_897_079, problem.solveFirst(5_764_801, 17_807_724))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(7_032_853, problem.solveFirst(input[0], input[1]))
    }
}
