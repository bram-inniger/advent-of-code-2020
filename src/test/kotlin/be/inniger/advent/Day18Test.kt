package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day18Test {

    private val problem = Day18()
    private val input = readInputFile("18")

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(71, problem.solveFirst(listOf("1 + 2 * 3 + 4 * 5 + 6")))
        assertEquals(51, problem.solveFirst(listOf("1 + (2 * 3) + (4 * (5 + 6))")))
        assertEquals(26, problem.solveFirst(listOf("2 * 3 + (4 * 5)")))
        assertEquals(437, problem.solveFirst(listOf("5 + (8 * 3 + 9 + 3 * 4 * 3)")))
        assertEquals(12240, problem.solveFirst(listOf("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")))
        assertEquals(13632, problem.solveFirst(listOf("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(3_348_222_486_398, problem.solveFirst(input))
    }
}
