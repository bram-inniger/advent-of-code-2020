package be.inniger.advent

import be.inniger.advent.Day08.Result.HaltState

class Day08 {

    fun solveFirst(programDescription: List<String>) =
        programDescription.mapIndexed { index, instruction -> Instruction.of(instruction, index) }
            .map { it.index to it }
            .toMap()
            .let { executeProgram(it, it.keys.maxOrNull()!!) }
            .acc

    fun solveSecond(programDescription: List<String>): Int {
        val program = programDescription.mapIndexed { index, instruction -> Instruction.of(instruction, index) }
            .map { it.index to it }
            .toMap()
        val lastInstruction = program.keys.maxOrNull()!!

        return (0..lastInstruction)
            .map { flipSingleInstruction(program, it) }
            .map { executeProgram(it, lastInstruction) }
            .single { it.status == HaltState.TERMINATED }
            .acc
    }

    private tailrec fun executeProgram(
        program: Map<Int, Instruction>,
        lastInstruction: Int,
        state: ProgramState = ProgramState(),
        visitedInstructions: Set<Int> = setOf()
    ): Result =
        if (state.pointer > lastInstruction || visitedInstructions.contains(state.pointer)) {
            val haltState = if (state.pointer > lastInstruction) HaltState.TERMINATED else HaltState.LOOPING

            Result(haltState, state.acc)
        } else {
            val nextState = program[state.pointer]!!.execute(state)
            val nextVisitedInstructions = visitedInstructions.plus(state.pointer)

            executeProgram(program, lastInstruction, nextState, nextVisitedInstructions)
        }

    private fun flipSingleInstruction(program: Map<Int, Instruction>, index: Int) =
        program.plus(index to program[index]!!.flip())

    private data class Instruction(val index: Int, val operation: Operation, val argument: Int) {

        enum class Operation { NOP, JMP, ACC }

        companion object {
            val regex = """^(\w{3}) ([+-]\d+)$""".toRegex()

            fun of(instructionDescription: String, index: Int): Instruction {
                val (operationString, argument) = regex.find(instructionDescription)!!.destructured
                val operation = when (operationString) {
                    "nop" -> Operation.NOP
                    "jmp" -> Operation.JMP
                    "acc" -> Operation.ACC
                    else -> error("Invalid instruction: $operationString")
                }

                return Instruction(index, operation, argument.toInt())
            }
        }

        fun execute(state: ProgramState): ProgramState = when (operation) {
            Operation.NOP -> ProgramState(state.pointer + 1, state.acc)
            Operation.JMP -> ProgramState(state.pointer + argument, state.acc)
            Operation.ACC -> ProgramState(state.pointer + 1, state.acc + argument)
        }

        fun flip() = Instruction(
            index,
            when (operation) {
                Operation.NOP -> Operation.JMP
                Operation.JMP -> Operation.NOP
                else -> operation
            },
            argument
        )
    }

    private data class ProgramState(val pointer: Int = 0, val acc: Int = 0)

    private data class Result(val status: HaltState, val acc: Int) {
        enum class HaltState { LOOPING, TERMINATED }
    }
}
