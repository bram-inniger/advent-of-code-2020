package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
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
}
