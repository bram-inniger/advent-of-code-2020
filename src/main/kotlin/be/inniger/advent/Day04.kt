package be.inniger.advent

class Day04 {

    fun solveFirst(passportsDescription: String) =
        passportsDescription.split("\n\n")
            .map { Passport.of(it) }
            .filter { it.isValid() }
            .count()

    private data class Passport(val fields: Set<String>) {

        companion object {
            private val requiredFields = listOf(
                "byr",
                "iyr",
                "eyr",
                "hgt",
                "hcl",
                "ecl",
                "pid"
            )

            internal fun of(passportDescription: String) =
                passportDescription.replace('\n', ' ')
                    .split(' ')
                    .map { it.substringBefore(':') }
                    .toSet()
                    .let { Passport(it) }
        }

        fun isValid() = requiredFields.all { fields.contains(it) }
    }
}
