package be.inniger.advent

import be.inniger.advent.util.maxOrThrow

class Day10 {

    fun solveFirst(adapters: List<Int>): Int {
        val jolts = listOf(0) +
                adapters.sorted() +
                (adapters.maxOrThrow() + 3)

        return (1..jolts.lastIndex)
            .map { jolts[it] - jolts[it - 1] }
            .groupingBy { it }
            .eachCount()
            .values
            .reduce(Int::times)
    }
}
