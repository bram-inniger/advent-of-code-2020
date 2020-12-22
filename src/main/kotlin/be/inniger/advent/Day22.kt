package be.inniger.advent

import be.inniger.advent.util.head
import be.inniger.advent.util.tail

class Day22 {

    fun solveFirst(decks: String): Int {
        val (player1, player2) = decks.split("\n\n")
            .map { deck -> deck.split("\n").drop(1).map { card -> card.toInt() } }

        return play(player1, player2)
            .mapIndexed { index, card -> card * (player1.size + player2.size - index) }
            .sum()
    }

    private tailrec fun play(player1: List<Int>, player2: List<Int>): List<Int> = when {
        player1.isEmpty() -> player2
        player2.isEmpty() -> player1
        else -> {
            val card1 = player1.head()
            val card2 = player2.head()

            when {
                card1 > card2 -> play(player1.tail() + card1 + card2, player2.tail())
                card1 < card2 -> play(player1.tail(), player2.tail() + card2 + card1)
                else -> error("Cannot have 2 cards with the same value: $card1, $card2")
            }
        }
    }
}
