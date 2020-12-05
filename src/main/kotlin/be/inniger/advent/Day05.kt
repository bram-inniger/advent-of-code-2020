package be.inniger.advent

class Day05 {

    private companion object {
        private const val MAX_ROW = 128
        private const val MAX_COL = 8
    }

    fun solveFirst(boardingPassDescriptions: List<String>) =
        boardingPassDescriptions
            .map { BoardingPass.of(it) }
            .maxOf { it.getSeatId() }

    fun solveSecond(boardingPassDescriptions: List<String>): Int {
        val seatsTaken = boardingPassDescriptions
            .map { BoardingPass.of(it) }
            .map { it.getSeatId() }
            .toSet()

        return (0..(MAX_ROW * MAX_COL))
            .single { seatsTaken.contains(it - 1) && !seatsTaken.contains(it) && seatsTaken.contains(it + 1) }
    }

    private data class BoardingPass(val row: Int, val col: Int) {

        companion object {
            private const val COLUMN_PART = 8
            private const val BINARY = 2
            private val zero = """([FL])""".toRegex()
            private val one = """([BR])""".toRegex()

            internal fun of(description: String) = description
                .let { zero.replace(it, "0") }
                .let { one.replace(it, "1") }
                .toInt(BINARY)
                .let { BoardingPass(it / COLUMN_PART, it % COLUMN_PART) }
        }

        fun getSeatId() = (row * MAX_COL) + col
    }
}
