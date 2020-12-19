package be.inniger.advent

class Day19 {

    private companion object {
        private val charRegex = """"(\w)"""".toRegex()
    }

    fun solveFirst(messageInfo: String): Int {
        val (rules, messages) = messageInfo.split("\n\n")

        val regex = parse(
            0,
            rules.split("\n")
                .map { it.split(": ") }
                .map { it[0].toInt() to it[1] }
                .toMap()
        ).toRegex()

        return messages.split("\n")
            .filter { regex.matches(it) }
            .count()
    }

    private fun parse(toFind: Int, rules: Map<Int, String>): String {
        val rule = rules[toFind]!!

        return when {
            charRegex.matches(rule) -> charRegex.find(rule)!!.groupValues[1]
            rule.contains("|") -> rule.split(" | ")
                .joinToString("|", "(", ")") { parseGroup(it, rules) }
            else -> parseGroup(rule, rules)
        }
    }

    private fun parseGroup(rule: String, rules: Map<Int, String>) =
        rule.split(" ").map { it.toInt() }.joinToString("") { parse(it, rules) }
}
