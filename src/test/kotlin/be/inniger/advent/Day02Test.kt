package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day02Test {

    private val problem = Day02()
    private val input = readInputFile("02")

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(
            2,
            problem.solveFirst(
                listOf(
                    "1-3 a: abcde",
                    "1-3 b: cdefg",
                    "2-9 c: ccccccccc"
                )
            )
        )
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(0, problem.solveFirst(input))
    }
}
