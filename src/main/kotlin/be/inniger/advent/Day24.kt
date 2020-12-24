package be.inniger.advent

class Day24 {

    fun solveFirst(instructions: List<String>) =
        instructions.map { move(it) }
            .groupingBy { it }
            .eachCount()
            .filterValues { it % 2 != 0 }
            .count()

    private tailrec fun move(instruction: String, position: Coordinate = Coordinate(0, 0)): Coordinate =
        if (instruction.isEmpty()) position
        else when {
            // e, se, sw, w, nw, and ne
            instruction.startsWith("e") -> move(instruction.substring(1), Coordinate(position.x + 2, position.y))
            instruction.startsWith("se") -> move(instruction.substring(2), Coordinate(position.x + 1, position.y - 1))
            instruction.startsWith("sw") -> move(instruction.substring(2), Coordinate(position.x - 1, position.y - 1))
            instruction.startsWith("w") -> move(instruction.substring(1), Coordinate(position.x - 2, position.y))
            instruction.startsWith("nw") -> move(instruction.substring(2), Coordinate(position.x - 1, position.y + 1))
            instruction.startsWith("ne") -> move(instruction.substring(2), Coordinate(position.x + 1, position.y + 1))
            else -> error("Invalid instruction: $instruction")
        }

    private data class Coordinate(val x: Int, val y: Int)
}
