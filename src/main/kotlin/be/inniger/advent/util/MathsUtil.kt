package be.inniger.advent.util

fun sqrt(number: Int): Int {
    val root = kotlin.math.sqrt(number.toDouble()).toInt()

    return if (root * root == number) root
    else error("Not a square number: $number")
}
