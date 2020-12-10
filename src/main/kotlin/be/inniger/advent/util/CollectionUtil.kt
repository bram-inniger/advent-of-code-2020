package be.inniger.advent.util

fun <T: Comparable<T>> List<T>.minOrThrow() = this.minOrNull() ?: error("Could not find min")

fun <T: Comparable<T>> List<T>.maxOrThrow() = this.maxOrNull() ?: error("Could not find max")
