package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day24Test {

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
        assertEquals(10, Day24.solveFirst(instructions))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(512, Day24.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(15, Day24.solveSecond(instructions, 1))
        assertEquals(12, Day24.solveSecond(instructions, 2))
        assertEquals(25, Day24.solveSecond(instructions, 3))
        assertEquals(14, Day24.solveSecond(instructions, 4))
        assertEquals(23, Day24.solveSecond(instructions, 5))
        assertEquals(28, Day24.solveSecond(instructions, 6))
        assertEquals(41, Day24.solveSecond(instructions, 7))
        assertEquals(37, Day24.solveSecond(instructions, 8))
        assertEquals(49, Day24.solveSecond(instructions, 9))
        assertEquals(37, Day24.solveSecond(instructions, 10))
        assertEquals(132, Day24.solveSecond(instructions, 20))
        assertEquals(259, Day24.solveSecond(instructions, 30))
        assertEquals(406, Day24.solveSecond(instructions, 40))
        assertEquals(566, Day24.solveSecond(instructions, 50))
        assertEquals(788, Day24.solveSecond(instructions, 60))
        assertEquals(1106, Day24.solveSecond(instructions, 70))
        assertEquals(1373, Day24.solveSecond(instructions, 80))
        assertEquals(1844, Day24.solveSecond(instructions, 90))
        assertEquals(2208, Day24.solveSecond(instructions, 100))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(4_120, Day24.solveSecond(input, 100))
    }
}
