package be.inniger.advent.util

fun <T : Comparable<T>> Collection<T>.minOrThrow() = this.minOrNull() ?: error("Could not find min")

fun <T : Comparable<T>> Collection<T>.maxOrThrow() = this.maxOrNull() ?: error("Could not find max")

fun <T> List<T>.head() = this.first()

fun <T> List<T>.tail() = this.subList(1, this.size)
