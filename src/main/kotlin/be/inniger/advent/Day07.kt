package be.inniger.advent

import be.inniger.advent.util.head
import be.inniger.advent.util.tail

object Day07 {

    private const val TO_FIND = "shiny gold"

    fun solveFirst(bagRules: List<String>) =
        bagRules.map { Rule.of(it) }
            .flatMap { rule ->
                rule.contents
                    .keys
                    .map { content -> content to rule.bag }
            }
            .groupingBy { it.first }
            .fold(setOf<String>()) { bags, container -> bags + container.second }
            .let { countContainerBags(it) }

    fun solveSecond(bagRules: List<String>): Int {
        return bagRules.map { Rule.of(it) }
            .associate { it.bag to it.contents }
            .let { countContainedBags(it) }
            .minus(1)
    }

    private tailrec fun countContainerBags(
        containerBags: Map<String, Set<String>>,
        toFind: List<String> = listOf(TO_FIND),
        foundContainers: Set<String> = setOf()
    ): Int =
        if (toFind.isEmpty()) foundContainers.size
        else {
            val nextBags = containerBags[toFind.head()] ?: setOf()
            val nextToFind = toFind.tail() + nextBags
            val nextFoundContainers = foundContainers + nextBags

            countContainerBags(containerBags, nextToFind, nextFoundContainers)
        }

    private fun countContainedBags(bagMapping: Map<String, Map<String, Int>>, toFind: String = TO_FIND): Int =
        bagMapping.getOrDefault(toFind, mapOf())
            .map { it.value * countContainedBags(bagMapping, it.key) }
            .sum()
            .plus(1)

    private data class Rule(val bag: String, val contents: Map<String, Int>) {

        companion object {
            private val fullRegex =
                """^(\w+ \w+) bags contain ((?:\d+ \w+ \w+ bags?(?:, )?)+|no other bags)\.$""".toRegex()
            private val contentRegex = """^(\d+) (\w+ \w+) bags?$""".toRegex()

            internal fun of(bagRule: String): Rule {
                val (bag, contents) = fullRegex.find(bagRule)!!.destructured
                val parsedContents =
                    if (contents == "no other bags") mapOf()
                    else contents
                        .split(", ")
                        .associate {
                            val (bagsCount, containedBag) = contentRegex.find(it)!!.destructured
                            containedBag to bagsCount.toInt()
                        }

                return Rule(bag, parsedContents)
            }
        }
    }
}
