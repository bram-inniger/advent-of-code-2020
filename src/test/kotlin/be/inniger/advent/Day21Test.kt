package be.inniger.advent

import be.inniger.advent.util.readInputFile
import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {

    private val input = readInputFile("21")
    private val foods = listOf(
        "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
        "trh fvjkl sbzzf mxmxvkd (contains dairy)",
        "sqjhc fvjkl (contains soy)",
        "sqjhc mxmxvkd sbzzf (contains fish)"
    )

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(5, Day21.solveFirst(foods))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(2_211, Day21.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals("mxmxvkd,sqjhc,fvjkl", Day21.solveSecond(foods))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals("vv,nlxsmb,rnbhjk,bvnkk,ttxvphb,qmkz,trmzkcfg,jpvz", Day21.solveSecond(input))
    }
}
