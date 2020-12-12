package be.inniger.advent

import be.inniger.advent.Day12.Action.*
import be.inniger.advent.Day12.Direction.*
import kotlin.math.abs

class Day12 {

    fun solveFirst(instructionsRaw: List<String>) =
        manoeuvre(
            Ship(EAST, 0, 0),
            instructionsRaw.map { Instruction.of(it) }
        )
            .let { abs(it.north) + abs(it.east) }

    private tailrec fun manoeuvre(ship: Ship, instructions: List<Instruction>): Ship =
        if (instructions.isEmpty()) ship
        else manoeuvre(
            move(ship, instructions.first()),
            instructions.subList(1, instructions.size)
        )

    private fun move(ship: Ship, instruction: Instruction): Ship {
        return when (instruction.action) {
            N -> Ship(ship.direction, ship.north + instruction.value, ship.east)
            S -> Ship(ship.direction, ship.north - instruction.value, ship.east)
            E -> Ship(ship.direction, ship.north, ship.east + instruction.value)
            W -> Ship(ship.direction, ship.north, ship.east - instruction.value)
            L -> Ship(ship.direction.rotate(-instruction.value), ship.north, ship.east)
            R -> Ship(ship.direction.rotate(instruction.value), ship.north, ship.east)
            F -> when (ship.direction) {
                NORTH -> Ship(ship.direction, ship.north + instruction.value, ship.east)
                SOUTH -> Ship(ship.direction, ship.north - instruction.value, ship.east)
                EAST -> Ship(ship.direction, ship.north, ship.east + instruction.value)
                WEST -> Ship(ship.direction, ship.north, ship.east - instruction.value)
            }
        }
    }

    private data class Instruction(val action: Action, val value: Int) {

        companion object {
            val regex = """^([NSEWLRF])(\d+)$""".toRegex()

            fun of(instruction: String): Instruction {
                val (action, value) = regex.find(instruction)!!.destructured

                return Instruction(Action.valueOf(action), value.toInt())
            }
        }
    }

    private data class Ship(val direction: Direction, val north: Int, val east: Int)

    private enum class Action { N, S, E, W, L, R, F }

    private enum class Direction(private val degrees: Int) {
        NORTH(0),
        SOUTH(180),
        EAST(90),
        WEST(270);

        fun rotate(value: Int): Direction =
            when ((degrees + value + 360) % 360) {
                0 -> NORTH
                90 -> EAST
                180 -> SOUTH
                270 -> WEST
                else -> error("Received invalid number of degrees: $value")
            }
    }
}
