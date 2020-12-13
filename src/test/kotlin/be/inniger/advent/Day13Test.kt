package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day13Test {

    private val problem = Day13()
    private val input = readInputFile("13")

    @Test
    fun validateFirstSampleInputs() {
        val notes = listOf(
            "939",
            "7,13,x,x,59,x,31,19"
        )

        assertEquals(295, problem.solveFirst(notes))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(222, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(1_068_781, problem.solveSecond("7,13,x,x,59,x,31,19"))
        assertEquals(3_417, problem.solveSecond("17,x,13,19"))
        assertEquals(754_018, problem.solveSecond("67,7,59,61"))
        assertEquals(779_210, problem.solveSecond("67,x,7,59,61"))
        assertEquals(1_261_476, problem.solveSecond("67,7,x,59,61"))
        assertEquals(1_202_161_486, problem.solveSecond("1789,37,47,1889"))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(408_270_049_879_073, problem.solveSecond(input[1]))
    }
}
