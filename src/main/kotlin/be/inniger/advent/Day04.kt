package be.inniger.advent

object Day04 {

    fun solveFirst(passportsDescription: String) =
        passportsDescription.split("\n\n")
            .map { Passport.of(it) }
            .filter { it.isWeaklyValid() }
            .count()

    fun solveSecond(passportsDescription: String) =
        passportsDescription.split("\n\n")
            .map { Passport.of(it) }
            .filter { it.isStronglyValid() }
            .count()

    private data class Passport(val fields: Map<String, String>) {

        companion object {
            private val rules = listOf(
                Rule("byr") { it.length == 4 && it.toInt() in 1920..2002 },
                Rule("iyr") { it.length == 4 && it.toInt() in 2010..2020 },
                Rule("eyr") { it.length == 4 && it.toInt() in 2020..2030 },
                Rule("hgt") {
                    when (it.substring(it.length - 2)) {
                        "cm" -> it.substringBefore("cm").toInt() in 150..193
                        "in" -> it.substringBefore("in").toInt() in 59..76
                        else -> false
                    }
                },
                Rule("hcl") { """^#[0-9a-f]{6}$""".toRegex().containsMatchIn(it) },
                Rule("ecl") { setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(it) },
                Rule("pid") { """^\d{9}$""".toRegex().containsMatchIn(it) }
            )

            internal fun of(passportDescription: String) =
                passportDescription.replace('\n', ' ')
                    .split(' ')
                    .map { it.substringBefore(':') to it.substringAfter(':') }
                    .toMap()
                    .let { Passport(it) }
        }

        fun isWeaklyValid() = rules.map { it.field }.all { fields.containsKey(it) }

        fun isStronglyValid() =
            rules.all { it.validator.invoke(fields.getOrDefault(it.field, "INVALID")) }
    }

    private data class Rule(val field: String, val validator: (String) -> Boolean)
}
