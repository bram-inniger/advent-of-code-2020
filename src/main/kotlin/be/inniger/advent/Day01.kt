package be.inniger.advent

object Day01 {

    private const val SUM = 2020

    fun solveFirst(expenses: List<Int>): Int {
        val expensesSet = expenses.toSet()

        return expenses
            .filter { expensesSet.contains(SUM - it) }
            .map { it * (SUM - it) }
            .distinct()
            .single()
    }

    fun solveSecond(expenses: List<Int>): Int {
        val expensesSet = expenses.toSet()

        return expenses
            .flatMap { first ->
                expenses
                    .filter { second -> expensesSet.contains(SUM - first - second) }
                    .map { second -> first * second * (SUM - first - second) }
            }
            .distinct()
            .single()
    }
}
