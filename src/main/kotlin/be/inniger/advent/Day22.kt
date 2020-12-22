package be.inniger.advent

import be.inniger.advent.util.head
import be.inniger.advent.util.tail

class Day22 {

    fun solveFirst(decks: String): Int {
        val (player1, player2) = decks.split("\n\n")
            .map { deck -> deck.split("\n").drop(1).map { card -> card.toInt() } }

        return score(combat(player1, player2))
    }

    fun solveSecond(decks: String): Int {
        val (player1, player2) = decks.split("\n\n")
            .map { deck -> deck.split("\n").drop(1).map { card -> card.toInt() } }

        return score(recursiveCombat(player1, player2).cards)
    }

    private tailrec fun combat(player1: List<Int>, player2: List<Int>): List<Int> = when {
        player1.isEmpty() -> player2
        player2.isEmpty() -> player1
        else -> {
            val card1 = player1.head()
            val card2 = player2.head()

            when {
                card1 > card2 -> combat(player1.tail() + card1 + card2, player2.tail())
                card1 < card2 -> combat(player1.tail(), player2.tail() + card2 + card1)
                else -> error("Cannot have 2 cards with the same value: $card1, $card2")
            }
        }
    }

    private fun recursiveCombat(
        cards1: List<Int>,
        cards2: List<Int>,
        cards1Prev: Set<List<Int>> = setOf(),
        cards2Prev: Set<List<Int>> = setOf()
    ): Result = when {
        cards1.isEmpty() -> Result(Player.TWO, cards2)
        cards2.isEmpty() -> Result(Player.ONE, cards1)
        cards1Prev.contains(cards1) && cards2Prev.contains(cards2) -> Result(Player.ONE)
        else -> {
            // Cannot do "cards1Prev + cards1" because of
            // https://youtrack.jetbrains.com/issue/KT-9992
            val newCards1prev = cards1Prev.plusElement(cards1)
            val newCards2prev = cards2Prev.plusElement(cards2)

            val card1 = cards1.head()
            val card2 = cards2.head()

            val winner = when {
                card1 < cards1.size && card2 < cards2.size -> {
                    recursiveCombat(cards1.subList(1, card1 + 1), cards2.subList(1, card2 + 1)).winner
                }
                card1 > card2 -> Player.ONE
                card1 < card2 -> Player.TWO
                else -> error("Cannot have 2 cards with the same value: $card1, $card2")
            }

            when (winner) {
                Player.ONE -> recursiveCombat(
                    cards1.tail() + card1 + card2,
                    cards2.tail(),
                    newCards1prev,
                    newCards2prev
                )
                Player.TWO -> recursiveCombat(
                    cards1.tail(),
                    cards2.tail() + card2 + card1,
                    newCards1prev,
                    newCards2prev
                )
            }
        }
    }

    private fun score(cards: List<Int>) =
        cards
            .mapIndexed { index, card -> card * (cards.size - index) }
            .sum()

    private enum class Player { ONE, TWO }

    private data class Result(val winner: Player, val cards: List<Int> = listOf())
}
