package be.inniger.advent

class Day07 {

    private companion object {
        private const val TO_FIND = "shiny gold"
    }

    fun solveFirst(bagRules: List<String>): Int {
        val rules = bagRules.map { Rule.of(it) }
        val bagMapping = mutableMapOf<String, MutableSet<String>>()

        for (rule in rules) {
            for (content in rule.contents) {
                val numberRemovedContent = content.substringAfter(' ')
                if (bagMapping.containsKey(numberRemovedContent)) {
                    bagMapping[numberRemovedContent]!!.add(rule.bag)
                } else {
                    bagMapping[numberRemovedContent] = mutableSetOf(rule.bag)
                }
            }
        }

        return countValidBags(bagMapping)
    }

    private tailrec fun countValidBags(
        bagMapping: MutableMap<String, MutableSet<String>>,
        toFind: List<String> = listOf(TO_FIND),
        validBags: Set<String> = setOf()
    ): Int =
        if (toFind.isEmpty()) {
            validBags.size
        } else {
            val nextValues = bagMapping[toFind[0]] ?: setOf()
            val nextToFind = toFind.minus(toFind[0]).plus(nextValues)
            val nextValidBags = validBags.plus(nextValues)

            countValidBags(bagMapping, nextToFind, nextValidBags)
        }

    private data class Rule(val bag: String, val contents: Set<String>) {

        companion object {
            private val regex = """^(\w+ \w+) bags contain (.*)\.$""".toRegex() // FIXME, cleaner regex

            internal fun of(bagRule: String): Rule {
                val (bag, contents) = regex.find(bagRule)!!.destructured
                val parsedContents =
                    if (contents == "no other bags") setOf()
                    else contents.split(", ").map { it.substringBefore(" bag") }.toSet()

                return Rule(bag, parsedContents)
            }
        }
    }
}
