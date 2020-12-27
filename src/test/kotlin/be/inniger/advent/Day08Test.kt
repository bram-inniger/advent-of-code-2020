package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day08Test {

    private val input = readInputFile("08")
    private val exampleProgram = listOf(
        "nop +0",
        "acc +1",
        "jmp +4",
        "acc +3",
        "jmp -3",
        "acc -99",
        "acc +1",
        "jmp -4",
        "acc +6"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(5, Day08.solveFirst(exampleProgram))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(1915, Day08.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(8, Day08.solveSecond(exampleProgram))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(944, Day08.solveSecond(input))
    }
}
