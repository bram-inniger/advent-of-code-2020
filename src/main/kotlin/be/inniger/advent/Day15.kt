package be.inniger.advent

class Day15 {

    companion object {
        private const val SPOKEN_INDEX = 2020
    }

    fun solveFirst(startingNumbers: List<Int>) =
        speak(
            1 + startingNumbers.size,
            0,
            startingNumbers.mapIndexed { index, number -> number to index + 1 }.toMap()
        )

    private tailrec fun speak(turn: Int, lastSpoken: Int, history: Map<Int, Int>): Int =
        if (turn == SPOKEN_INDEX) lastSpoken
        else speak(turn + 1, turn - (history[lastSpoken] ?: turn), history + (lastSpoken to turn))
}
