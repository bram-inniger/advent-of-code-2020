package be.inniger.advent.bonus

import org.junit.Test
import kotlin.test.assertEquals

class Bonus01Test {

    private val problem = Bonus01()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(1, problem.solveFirst(5))
        assertEquals(4, problem.solveFirst(104))
        assertEquals(10, problem.solveFirst(680))
        assertEquals(25, problem.solveFirst(4_325))
    }

    @Test
    fun validateFirstSolution() {
        val netherlandsPopulation = 17_479_043

        assertEquals(1581, problem.solveFirst(netherlandsPopulation))
    }
}
