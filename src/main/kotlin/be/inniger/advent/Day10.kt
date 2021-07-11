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
        tailrec fun findNrPaths(adapters: List<Int>, found: Map<Int, Long>): Map<Int, Long> =
            if (adapters.isEmpty()) found
            else findNrPaths(
                adapters.drop(1),
                found +
                        (adapters.first() to (1..3).sumOf { found.getOrDefault(adapters.first() - it, 0) })
            )

        val sortedAdapters = adaptersList.sorted()
        return findNrPaths(sortedAdapters, mapOf(0 to 1))[sortedAdapters.last()]!!
    }
}
