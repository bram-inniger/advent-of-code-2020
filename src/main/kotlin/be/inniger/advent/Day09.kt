package be.inniger.advent

class Day09 {

    fun solveFirst(xmas: List<Long>, preAmble: Int) =
        findFault(xmas, preAmble)

    fun solveSecond(xmas: List<Long>, preAmble: Int) =
        findSummingRange(findFault(xmas, preAmble), xmas)
            .let { it.minOrThrow() + it.maxOrThrow() }

    private fun findFault(xmas: List<Long>, preAmble: Int) =
        (preAmble until xmas.size)
            .first { isNotSumOfPair(it, xmas, preAmble) }
            .let { xmas[it] }

    private fun isNotSumOfPair(searchIndex: Int, xmas: List<Long>, preAmble: Int) =
        (searchIndex - preAmble until searchIndex)
            .flatMap { first ->
                (first + 1 until searchIndex)
                    .map { second -> first to second }
            }
            .map { xmas[it.first] to xmas[it.second] }
            .map { it.first + it.second }
            .none { it == xmas[searchIndex] }

    private fun findSummingRange(fault: Long, xmas: List<Long>) =
        (0 until xmas.size - 1)
            .map { findSummingRangeFromIndex(fault, xmas, it) }
            .first { it.found }
            .let { xmas.subList(it.from, it.to) }

    private tailrec fun findSummingRangeFromIndex(
        fault: Long,
        xmas: List<Long>,
        index: Int,
        jndex: Int = index + 1,
        sum: Long = xmas[index] + xmas[jndex]
    ): Result = when {
        sum > fault -> Result(-1, -1, false)
        sum == fault -> Result(index, jndex, true)
        else -> findSummingRangeFromIndex(fault, xmas, index, jndex + 1, sum + xmas[jndex + 1])
    }

    private data class Result(val from: Int, val to: Int, val found: Boolean)

    private fun List<Long>.minOrThrow() = this.minOrNull() ?: error("Could not find min")
    private fun List<Long>.maxOrThrow() = this.maxOrNull() ?: error("Could not find max")
}
