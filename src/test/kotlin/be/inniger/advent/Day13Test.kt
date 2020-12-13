package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day13Test {

    private val problem = Day13()
    private val input = readInputFile("13")
    private val notes = listOf(
        "939",
        "7,13,x,x,59,x,31,19"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(295, problem.solveFirst(notes))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(222, problem.solveFirst(input))
    }
}
