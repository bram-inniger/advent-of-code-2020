package be.inniger.advent

class Day23 {

    private companion object {
        private const val NR_TAKEN = 3
    }

    fun solveFirst(cups: List<Cup>, nrMoves: Int): String {
        val finalCups = play(cups, nrMoves)
        val oneIndex = finalCups.indexOf(1)

        return (finalCups.subList(oneIndex + 1, finalCups.size) + finalCups.subList(0, oneIndex))
            .joinToString("")
    }

    private tailrec fun play(cups: List<Cup>, nrMoves: Int): List<Cup> =
        if (nrMoves == 0) cups
        else {
            val taken = cups.subList(1, NR_TAKEN + 1)
            val destination = cups.indexOf(validDestination(cups.first() - 1, cups.size, taken))

            play(
                cups.subList(NR_TAKEN + 1, destination + 1) +
                        taken +
                        cups.subList(destination + 1, cups.size) +
                        cups.first(),
                nrMoves - 1
            )
        }

    private tailrec fun validDestination(destination: Cup, nrCups: Int, taken: List<Cup>): Cup = when {
        destination > 0 && !taken.contains(destination) -> destination
        destination <= 0 -> validDestination(destination + nrCups, nrCups, taken)
        else -> validDestination(destination - 1, nrCups, taken)
    }
}

private typealias Cup = Int
