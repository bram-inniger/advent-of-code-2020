package be.inniger.advent

class Day09 {

    fun solveFirst(xmas: List<Long>, preAmble: Int) =
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
}
