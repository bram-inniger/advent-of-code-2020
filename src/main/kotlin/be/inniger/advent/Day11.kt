package be.inniger.advent

object Day11 {

    fun solveFirst(gridString: List<String>) =
        solve(Result(Grid.of(gridString)), 4) { grid, coord -> nrOfOccupiedNeighbours(grid, coord) }

    fun solveSecond(gridString: List<String>) =
        solve(Result(Grid.of(gridString)), 5) { grid, coord -> nrOfOccupiedVisible(grid, coord) }

    private fun solve(result: Result, switchThreshold: Int, nrOccupied: (grid: Grid, coord: Coordinate) -> Int): Int =
        if (result.seatsChanged == 0) result.grid.grid.filter { it.value == Seat.O }.count()
        else solve(simulateNext(result.grid, switchThreshold, nrOccupied), switchThreshold, nrOccupied)

    private fun simulateNext(
        grid: Grid, switchThreshold: Int, nrOccupied: (grid: Grid, coord: Coordinate) -> Int
    ): Result {
        var changed = 0
        val newGrid = mutableMapOf<Coordinate, Seat>()

        for (coord in grid.grid.keys) {
            val seat = grid.grid[coord]!!
            val occupiedNeighbours = nrOccupied(grid, coord)

            val flip = (seat == Seat.E && occupiedNeighbours == 0) ||
                    (seat == Seat.O && occupiedNeighbours >= switchThreshold)

            newGrid[coord] = when (seat) {
                Seat.E -> if (flip) Seat.O else Seat.E
                Seat.O -> if (flip) Seat.E else Seat.O
                Seat.F -> error("Invalid entry found in map at $coord")
            }
            changed += if (flip) 1 else 0
        }

        return Result(Grid(newGrid, grid.width, grid.height), changed)
    }

    private fun nrOfOccupiedNeighbours(grid: Grid, center: Coordinate) =
        (-1..1).flatMap { x ->
            (-1..1).filter { y -> x != 0 || y != 0 }
                .map { y -> Coordinate(center.x + x, center.y + y) }
        }
            .filter { grid.grid.getOrDefault(it, Seat.F) == Seat.O }
            .count()

    private fun nrOfOccupiedVisible(grid: Grid, center: Coordinate) =
        (-1..1).flatMap { x ->
            (-1..1).filter { y -> x != 0 || y != 0 }
                .map { y -> Coordinate(x, y) }
                .map {
                    generateSequence(1) { it + 1 }
                        .map { i -> Coordinate(center.x + i * it.x, center.y + i * it.y) }
                        .first {
                            it.x !in 0..grid.width || it.y !in 0..grid.height ||
                                    grid.grid.getOrDefault(it, Seat.F) != Seat.F
                        }
                }
        }
            .filter { grid.grid.getOrDefault(it, Seat.F) == Seat.O }
            .count()

    private enum class Seat(private val display: Char) {
        F('.'),
        E('L'),
        O('#');

        override fun toString() = display.toString()

        companion object {
            fun of(display: Char) =
                when (display) {
                    '.' -> F
                    'L' -> E
                    '#' -> O
                    else -> error("Invalid seat: $display")
                }
        }
    }

    private data class Grid(val grid: Map<Coordinate, Seat>, val width: Int, val height: Int) {

        companion object {
            fun of(gridStrings: List<String>): Grid {
                val parsedGrid = gridStrings.map { it.toList() }
                val height = parsedGrid.lastIndex
                val width = parsedGrid[0].lastIndex

                val grid = (0..height)
                    .flatMap { y -> (0..width).map { x -> Coordinate(x, y) } }
                    .map { it to Seat.of(parsedGrid[it.y][it.x]) }
                    .filter { it.second != Seat.F }
                    .toMap()

                return Grid(grid, width, height)
            }
        }

        override fun toString() =
            (0..height).joinToString("\n") { y ->
                (0..width).map { x -> Coordinate(x, y) }
                    .map { grid.getOrDefault(it, Seat.F) }
                    .joinToString("") { it.toString() }
            }
    }

    private data class Coordinate(val x: Int, val y: Int)

    private data class Result(val grid: Grid, val seatsChanged: Int = Int.MAX_VALUE)
}
