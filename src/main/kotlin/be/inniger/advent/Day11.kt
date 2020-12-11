package be.inniger.advent

class Day11 {

    fun solveFirst(grid: List<String>): Int {
        var result = simulateNext(Grid.of(grid))

        while (result.seatsChanged > 0) {
            result = simulateNext(result.grid)
        }

        return result.grid
            .grid
            .filter { it.value == Seat.O }
            .count()
    }

    private fun simulateNext(grid: Grid): Result {
        var changed = 0
        val newGrid = mutableMapOf<Coordinate, Seat>()

        for (coordinate in grid.grid.keys) {
            val seat = grid.grid[coordinate]!!
            val occupiedNeighbours = nrOfOccupiedNeighbours(grid, coordinate)

            val flip = (seat == Seat.E && occupiedNeighbours == 0) ||
                    (seat == Seat.O && occupiedNeighbours >= 4)

            newGrid[coordinate] = when (seat) {
                Seat.E -> if (flip) Seat.O else Seat.E
                Seat.O -> if (flip) Seat.E else Seat.O
                Seat.F -> error("Invalid entry found in map at $coordinate")
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

    private data class Result(val grid: Grid, val seatsChanged: Int)
}
