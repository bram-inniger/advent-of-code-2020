package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {

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
        assertEquals(11, Day06.solveFirst(fullResponse))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(6273, Day06.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(6, Day06.solveSecond(fullResponse))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(3254, Day06.solveSecond(input))
    }
}
