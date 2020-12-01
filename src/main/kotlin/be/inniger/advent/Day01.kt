package be.inniger.advent

class Day01 {

    private companion object {
        private const val SUM = 2020
    }

    fun solveFirst(expenses: List<Int>): Int {
        val expensesSet = expenses.toSet()

        return expenses
            .filter { expensesSet.contains(SUM - it) }
            .map { it * (SUM - it) }
            .distinct()
            .single()
    }
}
