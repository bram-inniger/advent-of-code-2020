package be.inniger.advent

class Day18 {

    fun solveFirst(expressions: List<String>) =
        expressions.map { it.replace(" ", "") }.map { eval(it) }.sum()

    private fun eval(expression: String): Long {
        var all = mutableListOf<String>()
        var index = 0

        while (index < expression.length) {
            val value = expression[index]
            when {
                value.isDigit() || value == '+' || value == '*' -> {
                    all.add(value.toString())
                    index++
                }
                value == '(' -> {
                    val closingParen = findClosingParen(expression.substring(index))
                    all.add(eval(expression.substring(index + 1, index + closingParen)).toString())
                    index += closingParen + 1
                }
                else -> error("Trying to parse invalid value: $value")
            }
        }

        var answer = all[0].toLong()
        all = all.subList(1, all.size)

        while (all.isNotEmpty()) {
            val operation = all[0]
            val nextValue = all[1].toLong()

            when (operation) {
                "+" -> answer += nextValue
                "*" -> answer *= nextValue
                else -> error("Unsupported operation: $operation")
            }

            all = all.subList(2, all.size)
        }

        return answer
    }

    private fun findClosingParen(expression: String): Int {
        var openBrackets = 0

        for (index in expression.indices) {
            if (expression[index] == '(') openBrackets ++
            else if (expression[index] == ')') {
                if (openBrackets == 1) return index
                else openBrackets--
            }
        }

        error("Unbalanced parenthesis: $expression")
    }
}
