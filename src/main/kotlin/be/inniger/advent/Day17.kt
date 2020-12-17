package be.inniger.advent

class Day17 {

    private companion object {
        private const val NR_CYCLES = 6
    }

    fun solveFirst(initialState: List<String>) =
        simulate(
            initialState.indices.flatMap { y ->
                initialState.indices.map { x -> Coordinate(x, y, 0) }
            }
                .filter { initialState[it.y][it.x] == '#' }
                .toSet(),
            initialState.size
        ).size

    private tailrec fun simulate(activeCubes: Set<Coordinate>, startWidth: Int, turn: Int = 0): Set<Coordinate> =
        if (turn == NR_CYCLES) activeCubes
        else simulate(
            (0 - turn - 1..startWidth + turn + 1).flatMap { x ->
                (0 - turn - 1..startWidth + turn + 1).flatMap { y ->
                    (0 - turn - 1..turn + 1).map { z -> Coordinate(x, y, z) }
                }
            }
                .filter {
                    (activeCubes.contains(it) && nrActiveNeighbours(it, activeCubes) in 2..3) ||
                            (!activeCubes.contains(it) && nrActiveNeighbours(it, activeCubes) == 3)
                }
                .toSet(),
            startWidth,
            turn + 1)

    private fun nrActiveNeighbours(cube: Coordinate, allCubes: Set<Coordinate>) =
        (cube.x - 1..cube.x + 1).flatMap { x ->
            (cube.y - 1..cube.y + 1).flatMap { y ->
                (cube.z - 1..cube.z + 1).map { z -> Coordinate(x, y, z) }
            }
        }
            .filter { it.x != cube.x || it.y != cube.y || it.z != cube.z }
            .count { allCubes.contains(it) }

    private data class Coordinate(val x: Int, val y: Int, val z: Int)
}
