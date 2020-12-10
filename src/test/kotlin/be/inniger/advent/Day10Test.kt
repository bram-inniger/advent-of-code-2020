package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day10Test {

    private val problem = Day10()
    private val input = readInputFile("10").map { it.toInt() }

    @Test
    fun validateFirstSampleInputs() {
        val shortExample = listOf(
            16,
            10,
            15,
            5,
            1,
            11,
            7,
            19,
            6,
            12,
            4
        )
        val longerExample = listOf(
            28,
            33,
            18,
            42,
            31,
            14,
            46,
            20,
            48,
            47,
            24,
            23,
            49,
            45,
            19,
            38,
            39,
            11,
            1,
            32,
            25,
            35,
            8,
            17,
            7,
            9,
            4,
            2,
            34,
            10,
            3
        )

        assertEquals(35, problem.solveFirst(shortExample))
        assertEquals(220, problem.solveFirst(longerExample))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(2201, problem.solveFirst(input))
    }
}
