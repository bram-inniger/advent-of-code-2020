package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {

    private val input = readInputFile("07")
    private val exampleRules = listOf(
        "light red bags contain 1 bright white bag, 2 muted yellow bags.",
        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
        "bright white bags contain 1 shiny gold bag.",
        "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
        "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
        "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
        "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
        "faded blue bags contain no other bags.",
        "dotted black bags contain no other bags."
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(4, Day07.solveFirst(exampleRules))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(229, Day07.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        val otherExampleRules = listOf(
            "shiny gold bags contain 2 dark red bags.",
            "dark red bags contain 2 dark orange bags.",
            "dark orange bags contain 2 dark yellow bags.",
            "dark yellow bags contain 2 dark green bags.",
            "dark green bags contain 2 dark blue bags.",
            "dark blue bags contain 2 dark violet bags.",
            "dark violet bags contain no other bags."
        )

        assertEquals(32, Day07.solveSecond(exampleRules))
        assertEquals(126, Day07.solveSecond(otherExampleRules))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(6683, Day07.solveSecond(input))
    }
}
