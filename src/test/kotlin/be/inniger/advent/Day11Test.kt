package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day11Test {

    private val problem = Day11()
    private val input = readInputFile("11")
    private val grid = listOf(
        "L.LL.LL.LL",
        "LLLLLLL.LL",
        "L.L.L..L..",
        "LLLL.LL.LL",
        "L.LL.LL.LL",
        "L.LLLLL.LL",
        "..L.L.....",
        "LLLLLLLLLL",
        "L.LLLLLL.L",
        "L.LLLLL.LL"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(37, problem.solveFirst(grid))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(2_438, problem.solveFirst(input))
    }
}
