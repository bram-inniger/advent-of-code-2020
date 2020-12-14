package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
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
}
