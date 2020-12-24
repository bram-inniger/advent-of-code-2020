package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day24Test {

    private val problem = Day24()
    private val input = readInputFile("24")
    private val instructions = listOf(
        "sesenwnenenewseeswwswswwnenewsewsw",
        "neeenesenwnwwswnenewnwwsewnenwseswesw",
        "seswneswswsenwwnwse",
        "nwnwneseeswswnenewneswwnewseswneseene",
        "swweswneswnenwsewnwneneseenw",
        "eesenwseswswnenwswnwnwsewwnwsene",
        "sewnenenenesenwsewnenwwwse",
        "wenwwweseeeweswwwnwwe",
        "wsweesenenewnwwnwsenewsenwwsesesenwne",
        "neeswseenwwswnwswswnw",
        "nenwswwsewswnenenewsenwsenwnesesenew",
        "enewnwewneswsewnwswenweswnenwsenwsw",
        "sweneswneswneneenwnewenewwneswswnese",
        "swwesenesewenwneswnwwneseswwne",
        "enesenwswwswneneswsenwnewswseenwsese",
        "wnwnesenesenenwwnenwsewesewsesesew",
        "nenewswnwewswnenesenwnesewesw",
        "eneswnwswnwsenenwnwnwwseeswneewsenese",
        "neswnwewnwnwseenwseesewsenwsweewe",
        "wseweeenwnesenwwwswnew"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(10, problem.solveFirst(instructions))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(512, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(15, problem.solveSecond(instructions, 1))
        assertEquals(12, problem.solveSecond(instructions, 2))
        assertEquals(25, problem.solveSecond(instructions, 3))
        assertEquals(14, problem.solveSecond(instructions, 4))
        assertEquals(23, problem.solveSecond(instructions, 5))
        assertEquals(28, problem.solveSecond(instructions, 6))
        assertEquals(41, problem.solveSecond(instructions, 7))
        assertEquals(37, problem.solveSecond(instructions, 8))
        assertEquals(49, problem.solveSecond(instructions, 9))
        assertEquals(37, problem.solveSecond(instructions, 10))
        assertEquals(132, problem.solveSecond(instructions, 20))
        assertEquals(259, problem.solveSecond(instructions, 30))
        assertEquals(406, problem.solveSecond(instructions, 40))
        assertEquals(566, problem.solveSecond(instructions, 50))
        assertEquals(788, problem.solveSecond(instructions, 60))
        assertEquals(1106, problem.solveSecond(instructions, 70))
        assertEquals(1373, problem.solveSecond(instructions, 80))
        assertEquals(1844, problem.solveSecond(instructions, 90))
        assertEquals(2208, problem.solveSecond(instructions, 100))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(4_120, problem.solveSecond(input, 100))
    }
}
