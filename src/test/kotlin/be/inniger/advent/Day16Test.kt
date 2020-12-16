package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day16Test {

    private val problem = Day16()
    private val input = readInputFile("16").joinToString("\n")
    private val notes =
        """
            class: 1-3 or 5-7
            row: 6-11 or 33-44
            seat: 13-40 or 45-50

            your ticket:
            7,1,14

            nearby tickets:
            7,3,47
            40,4,50
            55,2,20
            38,6,12
        """.trimIndent()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(71, problem.solveFirst(notes))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(23_009, problem.solveFirst(input))
    }
}
