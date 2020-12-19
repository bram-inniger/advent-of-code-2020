package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day19Test {

    private val problem = Day19()
    private val input = readInputFile("19").joinToString("\n")
    private val messageInfo =
        """
            0: 4 1 5
            1: 2 3 | 3 2
            2: 4 4 | 5 5
            3: 4 5 | 5 4
            4: "a"
            5: "b"
            
            ababbb
            bababa
            abbbab
            aaabbb
            aaaabbb
        """.trimIndent()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(2, problem.solveFirst(messageInfo))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(156, problem.solveFirst(input))
    }
}
