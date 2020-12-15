package be.inniger.advent

class Day15 {

    fun solveFirst(startingNumbers: List<Int>): Int {
        val maxTurn = 2020

        tailrec fun speak(turn: Int, lastSpoken: Int, history: Map<Int, Int>): Int =
            if (turn == maxTurn) lastSpoken
            else speak(turn + 1, turn - (history[lastSpoken] ?: turn), history + (lastSpoken to turn))

        return speak(
            startingNumbers.size + 1,
            0,
            startingNumbers.mapIndexed { index, number -> number to index + 1 }.toMap()
        )
    }

    fun solveSecond(startingNumbers: List<Int>): Int {
        val maxTurn = 30_000_000
        val history = startingNumbers.mapIndexed { index, number -> number to index + 1 }.toMap().toMutableMap()

        var turn = startingNumbers.size + 1
        var lastSpoken = 0

        while (turn < maxTurn) {
            val newLastSpoken = turn - (history[lastSpoken] ?: turn)
            history[lastSpoken] = turn
            lastSpoken = newLastSpoken
            turn++
        }

        return lastSpoken
    }
}
