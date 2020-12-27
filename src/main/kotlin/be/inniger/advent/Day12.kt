package be.inniger.advent

import be.inniger.advent.Day12.Action.*
import be.inniger.advent.Day12.Direction.*
import be.inniger.advent.util.head
import be.inniger.advent.util.tail
import kotlin.math.abs

object Day12 {

    fun solveFirst(instructions: List<String>) =
        simpleManoeuvre(
            Ship(EAST, 0, 0),
            instructions.map { Instruction.of(it) }
        )
            .let { abs(it.north) + abs(it.east) }

    fun solveSecond(instructions: List<String>) =
        waypointManoeuvre(
            Ship(EAST, 0, 0),
            WayPoint(1, 10),
            instructions.map { Instruction.of(it) }
        )
            .let { abs(it.north) + abs(it.east) }

    private tailrec fun simpleManoeuvre(ship: Ship, instructions: List<Instruction>): Ship =
        if (instructions.isEmpty()) ship
        else simpleManoeuvre(
            simpleMove(ship, instructions.head()),
            instructions.tail()
        )

    private tailrec fun waypointManoeuvre(ship: Ship, wayPoint: WayPoint, instructions: List<Instruction>): Ship =
        if (instructions.isEmpty()) ship
        else waypointManoeuvre(
            shipMove(ship, wayPoint, instructions.head()),
            wayPointMove(wayPoint, instructions.head()),
            instructions.tail()
        )

    private fun simpleMove(ship: Ship, instruction: Instruction) =
        when (instruction.action) {
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

    private fun shipMove(ship: Ship, wayPoint: WayPoint, instruction: Instruction) =
        if (instruction.action == F)
            Ship(
                ship.direction,
                ship.north + instruction.value * wayPoint.north,
                ship.east + instruction.value * wayPoint.east
            )
        else ship

    private fun wayPointMove(wayPoint: WayPoint, instruction: Instruction) =
        when (instruction.action) {
            N -> WayPoint(wayPoint.north + instruction.value, wayPoint.east)
            S -> WayPoint(wayPoint.north - instruction.value, wayPoint.east)
            E -> WayPoint(wayPoint.north, wayPoint.east + instruction.value)
            W -> WayPoint(wayPoint.north, wayPoint.east - instruction.value)
            L -> when ((instruction.value + 360) % 360) {
                0 -> wayPoint
                90 -> WayPoint(wayPoint.east, -wayPoint.north)
                180 -> WayPoint(-wayPoint.north, -wayPoint.east)
                270 -> WayPoint(-wayPoint.east, wayPoint.north)
                else -> error("Illegal turn of ${instruction.value} degrees")
            }
            R -> when ((instruction.value + 360) % 360) {
                0 -> wayPoint
                90 -> WayPoint(-wayPoint.east, wayPoint.north)
                180 -> WayPoint(-wayPoint.north, -wayPoint.east)
                270 -> WayPoint(wayPoint.east, -wayPoint.north)
                else -> error("Illegal turn of ${instruction.value} degrees")
            }
            F -> wayPoint
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

    private data class WayPoint(val north: Int, val east: Int)

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
