package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {

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
        assertEquals(7, Day03.solveFirst(trees))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(284, Day03.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(336, Day03.solveSecond(trees))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(3_510_149_120, Day03.solveSecond(input))
    }
}
