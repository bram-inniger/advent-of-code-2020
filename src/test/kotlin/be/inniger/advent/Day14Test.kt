package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    private val problem = Day14()
    private val input = readInputFile("14")

    @Test
    fun validateFirstSampleInputs() {
        val program = listOf(
            "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
            "mem[8] = 11",
            "mem[7] = 101",
            "mem[8] = 0"
        )

        assertEquals(165, problem.solveFirst(program))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(8_332_632_930_672, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        val program = listOf(
            "mask = 000000000000000000000000000000X1001X",
            "mem[42] = 100",
            "mask = 00000000000000000000000000000000X0XX",
            "mem[26] = 1"
        )

        assertEquals(208, problem.solveSecond(program))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(4_753_238_784_664, problem.solveSecond(input))
    }
}
