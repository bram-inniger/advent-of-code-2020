package be.inniger.advent

import be.inniger.advent.Day14.Instruction.MaskInstruction
import be.inniger.advent.Day14.Instruction.MemInstruction
import be.inniger.advent.util.head
import be.inniger.advent.util.tail

class Day14 {

    private companion object {
        private const val INSTRUCTION_WIDTH = 36
    }

    fun solveFirst(program: List<String>) =
        runInit(
            program.map { Instruction.of(it) },
            "PLACEHOLDER",
            { instruction, mask -> listOf(instruction.address to overrideWithMask(mask, instruction)) }
        )

    fun solveSecond(program: List<String>) =
        runInit(
            program.map { Instruction.of(it) },
            "PLACEHOLDER",
            { instruction, mask ->
                generateAddresses(listOf(calculateMaskedAddress(instruction.address, mask)))
                    .map { it to instruction.value }
            }
        )


    private fun overrideWithMask(mask: String, instruction: MemInstruction) =
        (0 until INSTRUCTION_WIDTH)
            .map { if (mask[it] == 'X') instruction.value[it] else mask[it] }
            .joinToString("")

    private tailrec fun runInit(
        program: List<Instruction>,
        mask: String,
        nextMemory: (MemInstruction, String) -> Iterable<Pair<String, String>>,
        memory: Map<String, String> = mapOf()
    ): Long =
        if (program.isEmpty()) memory.values.map { it.toLong(2) }.sum()
        else when (val instruction = program.head()) {
            is MaskInstruction -> runInit(program.tail(), instruction.mask, nextMemory, memory)
            is MemInstruction -> runInit(program.tail(), mask, nextMemory, memory + nextMemory(instruction, mask))
        }

    private fun calculateMaskedAddress(address: String, mask: String) =
        (0 until INSTRUCTION_WIDTH)
            .map { if (mask[it] == '0') address[it] else mask[it] }
            .joinToString("")

    private tailrec fun generateAddresses(toFind: List<String>, addresses: List<String> = listOf()): List<String> =
        if (toFind.isEmpty()) addresses
        else {
            val address = toFind.head()

            if (address.contains('X'))
                generateAddresses(
                    toFind.tail() + address.replaceFirst('X', '0') + address.replaceFirst('X', '1'),
                    addresses
                )
            else generateAddresses(toFind.tail(), addresses + address)
        }

    private sealed class Instruction {

        companion object {
            fun of(instruction: String) = when {
                instruction.startsWith("mem") -> MemInstruction.of(instruction)
                instruction.startsWith("mask") -> MaskInstruction.of(instruction)
                else -> error("Received an invalid instruction: $instruction")
            }
        }

        data class MemInstruction(val address: String, val value: String) : Instruction() {

            companion object {
                private val regex = """^mem\[(\d+)] = (\d+)$""".toRegex()

                fun of(instruction: String): MemInstruction {
                    val (address, value) = regex.find(instruction)!!.destructured

                    return MemInstruction(asBinaryString(address), asBinaryString(value))
                }

                private fun asBinaryString(number: String) =
                    number.toLong().toString(2).padStart(INSTRUCTION_WIDTH, '0')
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
