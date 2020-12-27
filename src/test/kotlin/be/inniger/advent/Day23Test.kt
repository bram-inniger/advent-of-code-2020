package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {

    private val input = readInputFile("23").first().map { it - '0' }
    private val cups = listOf(3, 8, 9, 1, 2, 5, 4, 6, 7)

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(92_658_374, Day23.solveFirst(cups, 10))
        assertEquals(67_384_529, Day23.solveFirst(cups, 100))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(97_245_386, Day23.solveFirst(input, 100))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(149_245_887_792, Day23.solveSecond(cups, 10_000_000))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(156_180_332_979, Day23.solveSecond(input, 10_000_000))
    }
}
