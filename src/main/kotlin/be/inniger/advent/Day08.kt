package be.inniger.advent

class Day08 {

    fun solveFirst(programDescription: List<String>): Int {
        val program = programDescription.mapIndexed { index, instruction -> Instruction.of(instruction, index) }
            .map { it.index to it }
            .toMap()

        val visitedInstructions = mutableSetOf<Int>()
        var pointer = 0
        var acc = 0

        while (!visitedInstructions.contains(pointer)) {
            visitedInstructions.add(pointer)
            val instruction = program[pointer] ?: throw IndexOutOfBoundsException()
            when (instruction.operation) {
                "acc" -> {
                    acc += instruction.argument
                    pointer++
                }
                "jmp" -> pointer += instruction.argument
                else -> pointer++
            }
        }

        return acc
    }

    private data class Instruction(val index: Int, val operation: String, val argument: Int) {

        companion object {
            val regex = """^(\w{3}) ([+-]\d+)$""".toRegex()

            fun of(instructionDescription: String, index: Int): Instruction {
                val (operation, argument) = regex.find(instructionDescription)!!.destructured

                return Instruction(index, operation, argument.toInt())
            }
        }
    }
}
