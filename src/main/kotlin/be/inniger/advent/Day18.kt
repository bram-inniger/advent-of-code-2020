package be.inniger.advent

object Day18 {

    fun solveFirst(expressions: List<String>) =
        expressions.map { it.replace(" ", "") }.map { eval(it, setOf("+", "*"), setOf()).toLong() }.sum()

    fun solveSecond(expressions: List<String>) =
        expressions.map { it.replace(" ", "") }.map { eval(it, setOf("+"), setOf("*")).toLong() }.sum()

    @Suppress("ComplexRedundantLet")
    private fun eval(expression: String, first: Set<String>, second: Set<String>) =
        tokenize(expression, first, second)
            .let { resolveOperations(first, it) }
            .let { resolveOperations(second, it) }
            .single()

    private fun resolveOperations(operations: Set<String>, tokens: List<String>): List<String> {
        return if (operations.none { tokens.contains(it) }) tokens
        else {
            val toResolve = operations.map { it to tokens.indexOf(it) }
                .filter { it.second != -1 }
                .minByOrNull { it.second }!!
            val plus = toResolve.second
            val toInsert = when (val operation = toResolve.first) {
                "+" -> tokens[plus - 1].toLong() + tokens[plus + 1].toLong()
                "*" -> tokens[plus - 1].toLong() * tokens[plus + 1].toLong()
                else -> error("Invalid operation: $operation")
            }

            val newTokens = tokens.subList(0, plus - 1) + toInsert.toString() + tokens.subList(plus + 2, tokens.size)

            resolveOperations(operations, newTokens)
        }
    }

    private fun tokenize(expression: String, first: Set<String>, second: Set<String>): List<String> {
        val tokens = mutableListOf<String>()
        var index = 0

        while (index < expression.length) {
            val value = expression[index]
            when {
                value.isDigit() || value == '+' || value == '*' -> {
                    tokens.add(value.toString())
                    index++
                }
                value == '(' -> {
                    val closingParen = findClosingParen(expression.substring(index))
                    tokens += eval(expression.substring(index + 1, index + closingParen), first, second)
                    index += closingParen + 1
                }
                else -> error("Trying to parse invalid value: $value")
            }
        }

        return tokens
    }

    private fun findClosingParen(expression: String): Int {
        var openBrackets = 0

        for (index in expression.indices) {
            if (expression[index] == '(') openBrackets++
            else if (expression[index] == ')') {
                if (openBrackets == 1) return index
                else openBrackets--
            }
        }

        error("Unbalanced parenthesis: $expression")
    }
}
