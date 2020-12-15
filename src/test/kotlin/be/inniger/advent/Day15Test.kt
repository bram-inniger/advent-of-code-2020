package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day15Test {

    private val problem = Day15()
    private val input = readInputFile("15")[0].split(",").map { it.toInt() }

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(436, problem.solveFirst(listOf(0, 3, 6)))
        assertEquals(1, problem.solveFirst(listOf(1, 3, 2)))
        assertEquals(10, problem.solveFirst(listOf(2, 1, 3)))
        assertEquals(27, problem.solveFirst(listOf(1, 2, 3)))
        assertEquals(78, problem.solveFirst(listOf(2, 3, 1)))
        assertEquals(438, problem.solveFirst(listOf(3, 2, 1)))
        assertEquals(1836, problem.solveFirst(listOf(3, 1, 2)))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(257, problem.solveFirst(input))
    }
}
