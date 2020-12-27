package be.inniger.advent

object Day06 {

    fun solveFirst(fullResponse: String) =
        fullResponse.split("\n\n")
            .map { it.replace("\n", "") }
            .map { group -> group.map { it }.distinct().count() }
            .sum()

    fun solveSecond(fullResponse: String) =
        fullResponse.split("\n\n")
            .map { group ->
                group.split("\n")
                    .map { individual -> individual.toSet() }
                    .reduceRight { individual, common -> common.intersect(individual) }
            }
            .map { it.size }
            .sum()
}
