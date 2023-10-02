package com.vngrp

import java.io.File
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Extension functions for parsing files into useful data structures.
 * Credits: Heavily inspired by
 * https://github.com/Zordid/adventofcode-kotlin-2022/blob/21e3e3a432a0bf4a6b56b54e1d8bd87be5f4a7cb/src/main/kotlin/AdventOfCode.kt
 */
fun File.parseInts() = readLines().map { it.toInt() }

fun <T> String.chop(delimiter: String, transform: (from: String) -> T): Pair<T, T> {
    val (from, to) = split(delimiter)

    return transform(from) to transform(to)
}

fun time() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time

val Day<*>.input
    get() = File("src/main/kotlin/com/vngrp/aoc$year/input/day$day.txt")
val Day<*>.testInput
    get() = File("src/main/kotlin/com/vngrp/aoc$year/input/test-day$day.txt")

fun <T> List<T>.replace(old: T, new: T): List<T> {
    val index = indexOf(old)
    if (index < 0) return this

    val list = toMutableList()
    list[index] = new

    return list
}

fun Map<Int, Long>.merge(other: Map<Int, Long>): Map<Int, Long> {
    return (asSequence() + other.asSequence())
        .groupBy({ it.key }, { it.value })
        .mapValues { it.value.sum() }
}

fun List<Int>.min() = minOrNull()!!
fun List<Int>.max() = maxOrNull()!!

fun String.sorted() = toSortedSet().joinToString("")

fun Char.toAsciiInt() = Character.getNumericValue(this)

fun <T> List<T>.getAt(x: Int, y: Int, width: Int): T? {
    return if (x < 0 || x >= width || y < 0 || y >= width) {
        null
    } else {
        this[y * width + x]
    }
}

fun <T> Int.repeat(initialState: T, fold: (acc: T) -> T): T {
    return (0 until this).fold(initialState) { acc, _ -> fold(acc) }
}

val KClass<AdventOfCode>.editions
    get() = sealedSubclasses.mapNotNull { it.objectInstance }

fun <T : Any> assert(some: T, other: T) {
    if (some != other) {
        throw IllegalArgumentException("Expected $some to equal $other.")
    }
}