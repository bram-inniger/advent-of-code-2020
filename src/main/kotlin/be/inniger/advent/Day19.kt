package be.inniger.advent

object Day19 {

    private val charRegex = """"(\w)"""".toRegex()

    fun solveFirst(messageInfo: String) =
        solve(messageInfo)

    fun solveSecond(messageInfo: String) =
        solve(
            messageInfo,
            mapOf(
                8 to Override("42", "42 | 42 8"),
                11 to Override("42 31", "42 31 | 42 11 31")
            )
        )

    private fun solve(messageInfo: String, overrides: Map<Int, Override> = mapOf()): Int {
        val (rules, messages) = messageInfo.split("\n\n")

        val regex = parse(
            0,
            rules.split("\n")
                .map { it.split(": ") }
                .map { it[0].toInt() to it[1] }
                .toMap(),
            overrides
        ).toRegex()

        return messages.split("\n")
            .filter { regex.matches(it) }
            .count()
    }

    private fun parse(toFind: Int, rules: Map<Int, String>, overrides: Map<Int, Override> = mapOf()): String {
        val rule = overrides[toFind]?.rule ?: rules[toFind]!!
        val newOverrides = if (overrides[toFind] == null) overrides
        else overrides + (toFind to overrides[toFind]!!.incrementRunCount())

        return when {
            charRegex.matches(rule) -> charRegex.find(rule)!!.groupValues[1]
            rule.contains("|") -> rule.split(" | ")
                .joinToString("|", "(", ")") { parseGroup(it, rules, newOverrides) }
            else -> parseGroup(rule, rules, newOverrides)
        }
    }

    private fun parseGroup(rule: String, rules: Map<Int, String>, overrides: Map<Int, Override>) =
        rule.split(" ").map { it.toInt() }.joinToString("") { parse(it, rules, overrides) }

    private data class Override(val origRule: String, val overriddenRule: String, val runCount: Int = 0) {

        private companion object {
            private const val maxRuns = 4 // Empirically established, started at 20, made tests go green, and decreased
        }

        val rule
            get() = if (runCount < maxRuns) overriddenRule
            else origRule

        fun incrementRunCount() =
            Override(origRule, overriddenRule, runCount + 1)
    }
}
