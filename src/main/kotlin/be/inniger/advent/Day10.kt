package be.inniger.advent

import be.inniger.advent.util.maxOrThrow

object Day10 {

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

    fun solveSecond(adaptersList: List<Int>): Long {
        fun findNrPaths(adapters: Set<Int>, from: Int, cache: MutableMap<Int, Long>): Long =
            cache.getOrPut(from) {
                (1..3).map { from - it }
                    .filter { adapters.contains(it) }
                    .map { findNrPaths(adapters, it, cache) }
                    .sum()
            }

        return findNrPaths(adaptersList.toSet() + 0, adaptersList.maxOrThrow(), mutableMapOf(0 to 1))
    }
}
