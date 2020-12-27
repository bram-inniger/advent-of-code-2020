package be.inniger.advent

import be.inniger.advent.util.head
import be.inniger.advent.util.tail

object Day13 {

    fun solveFirst(notes: List<String>): Int {
        data class Departure(val busId: Int, val timeUntil: Int)

        val minDeparture = notes[0].toInt()

        return notes[1].split(",")
            .filter { it != "x" }
            .map { it.toInt() }
            .map { Departure(it, it - (minDeparture % it)) }
            .minByOrNull { it.timeUntil }!!
            .let { it.busId * it.timeUntil }
    }

    fun solveSecond(busIds: String) =
        busIds.split(",")
            .mapIndexed { offset, busId -> offset to busId }
            .filter { it.second != "x" }
            .map { Bus(it.first.toLong(), it.second.toLong()) }
            .let { searchFullSync(it) }

    private tailrec fun searchFullSync(schedule: List<Bus>, searchIncrement: Long = 1, time: Long = 0): Long =
        if (schedule.isEmpty()) time
        else searchFullSync(
            schedule.tail(),
            searchIncrement * schedule.head().busId,
            searchNextSync(schedule.head(), searchIncrement, time)
        )

    private tailrec fun searchNextSync(bus: Bus, searchIncrement: Long, time: Long): Long =
        if ((time + bus.offset) % bus.busId == 0L) time
        else searchNextSync(bus, searchIncrement, time + searchIncrement)

    private data class Bus(val offset: Long, val busId: Long)
}
