package be.inniger.advent

class Day05 {

    fun solveFirst(boardingPassDescriptions: List<String>) =
        boardingPassDescriptions
            .map { BoardingPass.of(it) }
            .maxOf { it.getSeatId() }

    private data class BoardingPass(val row: Int, val col: Int) {

        companion object {
            private const val MAX_ROW = 128
            private const val MAX_COL = 8

            internal fun of(description: String) = BoardingPass(
                partition(MAX_ROW, description.substring(0, 7)),
                partition(MAX_COL, description.substring(7))
            )

            private fun partition(rangeWidth: Int, directions: String): Int {
                var upperLimit = rangeWidth
                var lowerLimit = 0

                for (direction in directions) {
                    val difference = (upperLimit - lowerLimit) / 2

                    when (direction) {
                        'F', 'L' -> upperLimit -= difference
                        'B', 'R' -> lowerLimit += difference
                        else -> throw IllegalArgumentException("Illegal input $direction")
                    }
                }

                return lowerLimit
            }
        }

        fun getSeatId() = (row * MAX_COL) + col
    }
}
