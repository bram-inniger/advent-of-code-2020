package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {

    private val problem = Day23()
    private val input = readInputFile("23").first().map { it - '0' }
    private val cups = listOf(3, 8, 9, 1, 2, 5, 4, 6, 7)

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(92_658_374, problem.solveFirst(cups, 10))
        assertEquals(67_384_529, problem.solveFirst(cups, 100))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(97_245_386, problem.solveFirst(input, 100))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(149_245_887_792, problem.solveSecond(cups, 10_000_000))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(156_180_332_979, problem.solveSecond(input, 10_000_000))
    }
}
