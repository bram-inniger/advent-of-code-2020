package be.inniger.advent

import be.inniger.advent.Day08.Result.HaltState

object Day08 {

    fun solveFirst(program: List<String>) =
        execute(program).acc

    fun solveSecond(program: List<String>) =
        program.indices
            .reversed()
            .map { execute(program, it) }
            .first { it.status == HaltState.TERMINATED }
            .acc

    private fun execute(program: List<String>, toFlip: Int = -1): Result {
        var acc = 0
        var index = 0
        val visited = mutableSetOf<Int>()

        while (index < program.size && index !in visited) {
            val ins = if (index == toFlip) flip(program[index]) else program[index]
            val (op, arg) = ins.split(' ')

            visited += index
            acc += if (op == "acc") arg.toInt() else 0
            index += if (op == "jmp") arg.toInt() else 1
        }

        return Result(if (index >= program.size) HaltState.TERMINATED else HaltState.LOOPING, acc)
    }

    private fun flip(ins: String) =
        if (ins.startsWith("jmp")) ins.replace("jmp", "nop")
        else ins.replace("nop", "jmp")

    private data class Result(val status: HaltState, val acc: Int) {
        enum class HaltState { LOOPING, TERMINATED }
    }
}
