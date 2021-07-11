package be.inniger.advent

object Day06 {

    fun solveFirst(fullResponse: String) =
        fullResponse.split("\n\n")
            .map { it.replace("\n", "") }
            .sumOf { group -> group.map { it }.distinct().count() }

    fun solveSecond(fullResponse: String) =
        fullResponse.split("\n\n")
            .map { group ->
                group.split("\n")
                    .map { individual -> individual.toSet() }
                    .reduceRight { individual, common -> common.intersect(individual) }
            }
            .sumOf { it.size }
}
