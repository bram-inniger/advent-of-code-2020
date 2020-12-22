package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Test {

    private val problem = Day22()
    private val input = readInputFile("22").joinToString("\n")
    private val decks =
        """
            Player 1:
            9
            2
            6
            3
            1
            
            Player 2:
            5
            8
            4
            7
            10
        """.trimIndent()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(306, problem.solveFirst(decks))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(35_562, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(291, problem.solveSecond(decks))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(34_424, problem.solveSecond(input))
    }
}
