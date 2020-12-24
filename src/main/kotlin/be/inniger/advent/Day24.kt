package be.inniger.advent

class Day24 {

    fun solveFirst(instructions: List<String>) =
        parseBlackTiles(instructions).count()

    fun solveSecond(instructions: List<String>, nrDays: Int) =
        flip(parseBlackTiles(instructions), nrDays).count()

    private fun parseBlackTiles(instructions: List<String>) =
        instructions.map { move(it) }
            .groupingBy { it }
            .eachCount()
            .filterValues { it % 2 != 0 }
            .keys

    private tailrec fun move(instruction: String, position: Coordinate = Coordinate(0, 0)): Coordinate =
        if (instruction.isEmpty()) position
        else when {
            instruction.startsWith("e") -> move(instruction.substring(1), Coordinate(position.x + 2, position.y))
            instruction.startsWith("se") -> move(instruction.substring(2), Coordinate(position.x + 1, position.y - 1))
            instruction.startsWith("sw") -> move(instruction.substring(2), Coordinate(position.x - 1, position.y - 1))
            instruction.startsWith("w") -> move(instruction.substring(1), Coordinate(position.x - 2, position.y))
            instruction.startsWith("nw") -> move(instruction.substring(2), Coordinate(position.x - 1, position.y + 1))
            instruction.startsWith("ne") -> move(instruction.substring(2), Coordinate(position.x + 1, position.y + 1))
            else -> error("Invalid instruction: $instruction")
        }

    private tailrec fun flip(blackTiles: Set<Coordinate>, nrDays: Int): Set<Coordinate> =
        if (nrDays == 0) blackTiles
        else flip(
            blackTiles.flatMap { getNeighbours(it) + it }
                .distinct()
                .filter {
                    val blackNeighbours = nrOfBlackNeighbours(blackTiles, it)
                    val blackRemainsBlack = blackTiles.contains(it) && blackNeighbours in 1..2
                    val whiteBecomesBlack = !blackTiles.contains(it) && blackNeighbours == 2

                    blackRemainsBlack || whiteBecomesBlack
                }
                .toSet(),
            nrDays - 1
        )

    private fun nrOfBlackNeighbours(blackTiles: Set<Coordinate>, position: Coordinate): Int =
        getNeighbours(position)
            .filter { blackTiles.contains(it) }
            .count()

    private fun getNeighbours(position: Coordinate) = listOf(
        Coordinate(position.x + 2, position.y),
        Coordinate(position.x + 1, position.y - 1),
        Coordinate(position.x - 1, position.y - 1),
        Coordinate(position.x - 2, position.y),
        Coordinate(position.x - 1, position.y + 1),
        Coordinate(position.x + 1, position.y + 1)
    )

    private data class Coordinate(val x: Int, val y: Int)
}
