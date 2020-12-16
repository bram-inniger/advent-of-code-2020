package be.inniger.advent

class Day16 {

    fun solveFirst(notes: String): Int {
        val (fieldsRaw, _, nearbyTicketsRaw) = notes.split("\n\n")

        val ranges = Field.allOf(fieldsRaw)
            .flatMap { listOf(it.range1, it.range2) }

        return Ticket.allOf(nearbyTicketsRaw)
            .flatMap { it.values }
            .filter { value -> ranges.none { range -> range.contains(value) } }
            .sum()
    }

    fun solveSecond(notes: String): Long {
        val (fieldsRaw, yourTicketRaw, nearbyTicketsRaw) = notes.split("\n\n")

        val fields = Field.allOf(fieldsRaw)
        val ranges = fields
            .flatMap { listOf(it.range1, it.range2) }
        val yourTicket = Ticket.of(yourTicketRaw)
        val validNearbyTickets = Ticket.allOf(nearbyTicketsRaw)
            .filter { it.values.all { value -> ranges.any { range -> range.contains(value) } } }

        return findPositions(fields, validNearbyTickets)
            .filter { it.field.name.startsWith("departure") }
            .map { it.position }
            .map { yourTicket.values[it].toLong() }
            .reduce(Long::times)
    }

    private tailrec fun findPositions(
        fields: List<Field>,
        tickets: List<Ticket>,
        toFind: Set<Int> = fields.indices.toSet(),
        found: List<Result> = listOf()
    ): List<Result> =
        if (toFind.isEmpty()) found
        else {
            val result = findNextUniquePosition(toFind, fields, tickets)
            findPositions(fields, tickets, toFind - result.position, found + result)
        }

    private fun findNextUniquePosition(toFind: Set<Int>, fields: List<Field>, tickets: List<Ticket>) =
        fields
            .map { field -> field to findAllMatchingPositions(toFind, tickets, field) }
            .single { it.second.size == 1 }
            .let { Result(it.first, it.second.single()) }

    private fun findAllMatchingPositions(toFind: Set<Int>, tickets: List<Ticket>, field: Field) =
        toFind.filter { position ->
            tickets.all { ticket ->
                field.range1.contains(ticket.values[position]) || field.range2.contains(ticket.values[position])
            }
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
            fun of(ticket: String): Ticket =
                ticket.split("\n")
                    .filter { it != "your ticket:" }
                    .map { it.split(",").map(String::toInt) }
                    .map { Ticket(it) }
                    .single()

            fun allOf(tickets: String): List<Ticket> =
                tickets.split("\n")
                    .filter { it != "nearby tickets:" }
                    .map { it.split(",").map(String::toInt) }
                    .map { Ticket(it) }
        }
    }

    private data class Result(val field: Field, val position: Int)
}
