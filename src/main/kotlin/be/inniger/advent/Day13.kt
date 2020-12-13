package be.inniger.advent

class Day13 {

    fun solveFirst(notes: List<String>): Int {
        val minDeparture = notes[0].toInt()

        return notes[1].split(",")
            .filter { it != "x" }
            .map { it.toInt() }
            .map { Departure(it, it - (minDeparture % it)) }
            .minByOrNull { it.timeUntil }!!
            .let { it.busId * it.timeUntil }
    }

    private data class Departure(val busId: Int, val timeUntil: Int)
}
