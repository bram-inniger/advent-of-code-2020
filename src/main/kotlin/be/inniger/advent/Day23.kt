package be.inniger.advent

object Day23 {

    fun solveFirst(orig: List<Int>, nrMoves: Int): Int {
        val cups = solve(orig, nrMoves, 9)

        var result = 0
        var afterOne = cups[1].next
        for (i in 1..orig.lastIndex) {
            result = 10 * result + afterOne.label
            afterOne = afterOne.next
        }

        return result
    }

    fun solveSecond(orig: List<Int>, nrMoves: Int): Long {
        val cups = solve(orig, nrMoves, 1_000_000)

        return cups[1].next.label.toLong() *
                cups[1].next.next.label.toLong()
    }

    private fun solve(orig: List<Int>, nrMoves: Int, totalNrCups: Int): Array<Cup> {
        // Useful to keep at index 0, so the "cups" array can be treated as 1-indexed
        val dummy = Cup(-1)
        val cups = Array(totalNrCups + 1) { dummy }

        for (i in cups.indices) {
            cups[i] = Cup(i)
        }

        for (i in orig.size + 1..cups.lastIndex) {
            cups[i].next = cups[(i + 1) % cups.size]
        }

        for (i in orig.indices) {
            cups[orig[i]].next = cups[orig[(i + 1) % orig.size]]
        }

        // Account for the added cups
        if (totalNrCups > orig.size) {
            cups[0].next = dummy.next
            cups[orig[orig.lastIndex]].next = cups[orig.size + 1]
            cups[cups.lastIndex].next = cups[orig[0]]
        }

        play(cups[orig[0]], cups, nrMoves)

        return cups
    }

    private tailrec fun play(current: Cup, cups: Array<Cup>, nrMoves: Int) {
        if (nrMoves == 0) return
        else {
            val taken = current.next
            val removedCups = listOf(taken.label, taken.next.label, taken.next.next.label)
            val destination = cups[validDestination(current.label - 1, cups.size - 1, removedCups)]

            // Re-wire LinkedList where elements were removed
            current.next = taken.next.next.next
            // Re-wire LinkedList where those elements were re-inserted
            taken.next.next.next = destination.next
            destination.next = taken

            play(current.next, cups, nrMoves - 1)
        }
    }

    private tailrec fun validDestination(destination: Int, nrCups: Int, taken: List<Int>): Int =
        when {
            destination > 0 && !taken.contains(destination) -> destination
            destination <= 0 -> validDestination(destination + nrCups, nrCups, taken)
            else -> validDestination(destination - 1, nrCups, taken)
        }

    private class Cup(val label: Int) {
        var next: Cup = this
    }
}
