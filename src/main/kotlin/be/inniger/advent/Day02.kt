package be.inniger.advent

class Day02 {

    fun solveFirst(passwords: List<String>) = passwords
        .map { Password.of(it) }
        .filter { it.isValidWithOldPolicy() }
        .count()

    fun solveSecond(passwords: List<String>) = passwords
        .map { Password.of(it) }
        .filter { it.isValidWithNewPolicy() }
        .count()

    private data class Password(val lower: Int, val upper: Int, val letter: Char, val plainText: String) {

        companion object {
            private val regex = """^(\d+)-(\d+) ([a-z]): ([a-z]+)$""".toRegex()

            fun of(password: String): Password {
                val (lower, upper, letter, plainText) = regex.find(password)!!.destructured

                return Password(lower.toInt(), upper.toInt(), letter.single(), plainText)
            }
        }

        fun isValidWithOldPolicy() = plainText
            .toCharArray()
            .filter { it == letter }
            .count()
            .let { it in lower..upper }

        fun isValidWithNewPolicy(): Boolean {
            val first = plainText[lower - 1]
            val second = plainText[upper - 1]

            return (first == letter && second != letter) ||
                    (first != letter && second == letter)
        }
    }
}
