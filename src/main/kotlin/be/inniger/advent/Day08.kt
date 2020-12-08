package be.inniger.advent

import be.inniger.advent.Day08.Result.HaltState

class Day08 {

    fun solveFirst(program: List<String>) =
        execute(program).acc

    fun solveSecond(program: List<String>) =
        program.indices
            .map { flip(program, it) }
            .map { execute(it) }
            .single { it.status == HaltState.TERMINATED }
            .acc

    private fun execute(program: List<String>): Result {
        var acc = 0
        var index = 0
        val visited = mutableSetOf<Int>()

        while (index < program.size && index !in visited) {
            val (ins, arg) = program[index].split(' ')

            visited += index
            acc += if (ins == "acc") arg.toInt() else 0
            index += if (ins == "jmp") arg.toInt() else 1
        }

        return Result(if (index >= program.size) HaltState.TERMINATED else HaltState.LOOPING, acc)
    }

    private fun flip(program: List<String>, toFlip: Int): List<String> {
        val toFlipIns = program[toFlip]
        val flippedIns = when {
            toFlipIns.startsWith("jmp") -> toFlipIns.replace("jmp", "nop")
            toFlipIns.startsWith("nop") -> toFlipIns.replace("nop", "jmp")
            else -> toFlipIns
        }

        return program.mapIndexed { index, ins -> if (index == toFlip) flippedIns else ins }
    }

    private data class Result(val status: HaltState, val acc: Int) {
        enum class HaltState { LOOPING, TERMINATED }
    }
}
