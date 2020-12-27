package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day18Test {

    private val input = readInputFile("18")

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(71, Day18.solveFirst(listOf("1 + 2 * 3 + 4 * 5 + 6")))
        assertEquals(51, Day18.solveFirst(listOf("1 + (2 * 3) + (4 * (5 + 6))")))
        assertEquals(26, Day18.solveFirst(listOf("2 * 3 + (4 * 5)")))
        assertEquals(437, Day18.solveFirst(listOf("5 + (8 * 3 + 9 + 3 * 4 * 3)")))
        assertEquals(12_240, Day18.solveFirst(listOf("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")))
        assertEquals(13_632, Day18.solveFirst(listOf("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(3_348_222_486_398, Day18.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(231, Day18.solveSecond(listOf("1 + 2 * 3 + 4 * 5 + 6")))
        assertEquals(51, Day18.solveSecond(listOf("1 + (2 * 3) + (4 * (5 + 6))")))
        assertEquals(46, Day18.solveSecond(listOf("2 * 3 + (4 * 5)")))
        assertEquals(1_445, Day18.solveSecond(listOf("5 + (8 * 3 + 9 + 3 * 4 * 3)")))
        assertEquals(669_060, Day18.solveSecond(listOf("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")))
        assertEquals(23_340, Day18.solveSecond(listOf("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(43_423_343_619_505, Day18.solveSecond(input))
    }
}
