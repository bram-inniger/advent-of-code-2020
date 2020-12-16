package be.inniger.advent

class Day16 {

    fun solveFirst(notes: String): Int {
        val (fieldsRaw, _, nearbyTicketsRaw) = notes.split("\n\n")

        val ranges = Field.allOf(fieldsRaw)
            .flatMap { listOf(it.range1, it.range2) }

        return Ticket.allNearbyOf(nearbyTicketsRaw)
            .flatMap { it.values }
            .filter { value -> ranges.none { range -> range.contains(value) } }
            .sum()
    }

    private data class Field(val name: String, val range1: IntRange, val range2: IntRange) {

        companion object {
            private val regex = """^([a-z ]+): (\d+-\d+) or (\d+-\d+)$""".toRegex()

            fun allOf(fields: String): List<Field> =
                fields.split("\n")
                    .map {
                        val (name, range1, range2) = regex.find(it)!!.destructured
                        Field(name, asIntRange(range1), asIntRange(range2))
                    }

            private fun asIntRange(range: String): IntRange {
                val (from, to) = range.split("-")
                return from.toInt()..to.toInt()
            }
        }
    }

    private data class Ticket(val values: List<Int>) {

        companion object {
            fun allNearbyOf(tickets: String): List<Ticket> =
                tickets.split("\n")
                    .filter { it != "nearby tickets:" }
                    .map { it.split(",").map(String::toInt) }
                    .map { Ticket(it) }
        }
    }
}
