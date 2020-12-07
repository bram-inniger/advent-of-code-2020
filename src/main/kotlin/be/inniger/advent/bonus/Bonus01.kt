package be.inniger.advent.bonus

import kotlin.math.ceil
import kotlin.math.sqrt

class Bonus01 {

    fun solveFirst(population: Int) =
        getMinimalSideLength(population)

    /*
     * Using pen and paper it is clear that, for a given `n` number of sides,
     * the amount of packages equals `7*n^2 - 2n`.
     *
     * Solving `7*n^2 - 2n > population` keeping the positive root of this quadratic equation yields the answer.
     * As a reminder, for an equation `a*x^2 + b*x + c = 0`,
     * the solution is in the form `(-b +- sqrt(b^2 - 4*a*c)) / (2*a)
     */
    private fun getMinimalSideLength(population: Int) =
        ceil((2.0 + sqrt(4.0 + 28.0 * population)) / 14).toInt()
}
