package be.inniger.advent

class Day06 {

    fun solveFirst(fullResponse: String) =
        fullResponse.split("\n\n")
            .map { it.replace("\n", "") }
            .map { group -> group.map { it }.distinct().count() }
            .sum()
}
