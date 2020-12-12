package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day12Test {

    private val problem = Day12()
    private val input = readInputFile("12")
    private val instructions = listOf(
        "F10",
        "N3",
        "F7",
        "R90",
        "F11"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(25, problem.solveFirst(instructions))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(415, problem.solveFirst(input))
    }
}
