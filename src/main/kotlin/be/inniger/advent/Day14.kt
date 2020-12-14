package be.inniger.advent

import be.inniger.advent.util.head
import be.inniger.advent.util.tail

class Day14 {

    private companion object {
        private const val INSTRUCTION_WIDTH = 36
    }

    fun solveFirst(program: List<String>) =
        runInit(program.map { Instruction.of(it) }, "PLACEHOLDER", mapOf())

    private tailrec fun runInit(program: List<Instruction>, mask: String, memory: Map<Long, Long>): Long =
        if (program.isEmpty()) memory.values.sum()
        else when (val instruction = program.head()) {
            is Instruction.MaskInstruction -> runInit(program.tail(), instruction.mask, memory)
            is Instruction.MemInstruction -> runInit(
                program.tail(),
                mask,
                memory.plus(
                    instruction.address to
                            (0 until INSTRUCTION_WIDTH)
                                .map { if (mask[it] == 'X') instruction.value[it] else mask[it] }
                                .joinToString("")
                                .toLong(2)))
        }

    private sealed class Instruction {

        companion object {
            fun of(instruction: String): Instruction {
                return when {
                    instruction.startsWith("mem") -> MemInstruction.of(instruction)
                    instruction.startsWith("mask") -> MaskInstruction.of(instruction)
                    else -> error("Received an invalid instruction: $instruction")
                }
            }
        }

        data class MemInstruction(val address: Long, val value: String) : Instruction() {

            companion object {
                private val regex = """^mem\[(\d+)] = (\d+)$""".toRegex()

                fun of(instruction: String): MemInstruction {
                    val (address, value) = regex.find(instruction)!!.destructured

                    return MemInstruction(
                        address.toLong(),
                        value.toLong().toString(2).padStart(INSTRUCTION_WIDTH, '0')
                    )
                }
            }
        }

        data class MaskInstruction(val mask: String) : Instruction() {

            companion object {
                private val regex = """^mask = ([01X]+)$""".toRegex()

                fun of(instruction: String): MaskInstruction {
                    val (mask) = regex.find(instruction)!!.destructured

                    return MaskInstruction(mask)
                }
            }
        }
    }
}
