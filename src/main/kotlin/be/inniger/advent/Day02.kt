package be.inniger.advent

class Day02 {

    fun solveFirst(passwords: List<String>) = passwords
        .map { Password.of(it) }
        .filter { it.isValid() }
        .count()

    private data class Password(val policyMin: Int, val policyMax: Int, val letter: Char, val plainText: String) {

        companion object {
            private val regex = """^(\d+)-(\d+) ([a-z]): ([a-z]+)$""".toRegex()

            fun of(password: String): Password {
                val (policyMin, policyMax, letter, plainText) = regex.find(password)!!.destructured

                return Password(policyMin.toInt(), policyMax.toInt(), letter.single(), plainText)
            }
        }

        fun isValid() = plainText
            .toCharArray()
            .filter { it == letter }
            .count()
            .let { it in policyMin..policyMax }
    }
}
