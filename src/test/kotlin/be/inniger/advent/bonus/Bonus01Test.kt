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
        val netherlandsPopulation = 17_479_043L

        assertEquals(1581, problem.solveFirst(netherlandsPopulation))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(8, problem.solveSecond(listOf(5)))
        assertEquals(32, problem.solveSecond(listOf(104)))
        assertEquals(200, problem.solveSecond(listOf(4_325)))
    }

    @Test
    fun validateSecondSolution() {
        val populations = mapOf<String, Long>(
            "Asia" to 4_541_609_892,
            "Africa" to 1_340_869_106,
            "Europe" to 747_751_712,
            "South America" to 430_857_328,
            "North America" to 368_983_009,
            "Oceania" to 42_705_675
        )

        assertEquals(537816, problem.solveSecond(populations.values))
    }
}
