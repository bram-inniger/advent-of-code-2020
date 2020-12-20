package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {

    private val problem = Day03()
    private val input = readInputFile("03")
    private val trees = listOf(
        "..##.......",
        "#...#...#..",
        ".#....#..#.",
        "..#.#...#.#",
        ".#...##..#.",
        "..#.##.....",
        ".#.#.#....#",
        ".#........#",
        "#.##...#...",
        "#...##....#",
        ".#..#...#.#"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(7, problem.solveFirst(trees))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(284, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(336, problem.solveSecond(trees))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(3_510_149_120, problem.solveSecond(input))
    }
}
