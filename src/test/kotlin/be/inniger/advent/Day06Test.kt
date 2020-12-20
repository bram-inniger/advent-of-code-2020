package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {

    private val problem = Day06()
    private val input = readInputFile("06").joinToString("\n")
    private val fullResponse =
        """
            abc
            
            a
            b
            c
            
            ab
            ac
            
            a
            a
            a
            a
            
            b
        """.trimIndent()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(11, problem.solveFirst(fullResponse))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(6273, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(6, problem.solveSecond(fullResponse))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(3254, problem.solveSecond(input))
    }
}
