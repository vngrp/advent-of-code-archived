package com.vngrp

import com.vngrp.aoc2015.AdventOfCode2015
import com.vngrp.aoc2016.AdventOfCode2016
import com.vngrp.aoc2017.AdventOfCode2017
import com.vngrp.aoc2018.AdventOfCode2018
import com.vngrp.aoc2019.AdventOfCode2019
import com.vngrp.aoc2020.AdventOfCode2020
import com.vngrp.aoc2021.AdventOfCode2021
import com.vngrp.aoc2022.AdventOfCode2022
import com.vngrp.aoc2023.AdventOfCode2023
import java.io.File
import kotlin.reflect.jvm.jvmName
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime.Companion.parse
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Extension functions for parsing files into useful data structures.
 * Inspired by
 * https://github.com/Zordid/adventofcode-kotlin-2022/blob/21e3e3a432a0bf4a6b56b54e1d8bd87be5f4a7cb/src/main/kotlin/AdventOfCode.kt
 */
fun parseInts(file: File) = file.readLines().map { it.toInt() }
fun parseChars(file: File) = file.readText().toCharArray().toList()
fun parseBits(file: File) = file.readLines().map { it.map { char -> char == '1' } }
fun parseGroups(file: File, predicate: (String) -> Boolean) = file.readLines().chunked { predicate(it) }

fun List<String>.parseInts() = map { it.toInt() }
fun String.parseChars() = toCharArray().toList()
fun List<String>.parseBits() = map { it.map { char -> char == '1' } }

/**
 * Allow destructuring characters on string
 */
operator fun String.component1() = first()
operator fun String.component2() = drop(1).first()
operator fun String.component3() = drop(2).first()
operator fun String.component4() = drop(3).first()
operator fun String.component5() = drop(4).first()
operator fun String.component6() = drop(5).first()

/**
 * Helpful data representations
 */
data class Point(val x: Int, val y: Int) {
    constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)
}

fun <T> List<T>.chunked(predicate: (T) -> Boolean) = fold(listOf<List<T>>()) { chunks, element ->
    if (predicate(element)) {
        val last = chunks.lastOrNull() ?: emptyList()

        chunks.dropLast(1) + listOf(last + element)
    } else {
        chunks + listOf(emptyList())
    }
}

fun <T> List<T>.replace(old: T, new: T): List<T> {
    val index = indexOf(old)
    if (index < 0) return this

    val list = toMutableList()
    list[index] = new

    return list
}

fun <T> List<T>.getAt(x: Int, y: Int, width: Int): T? {
    return if (x < 0 || x >= width || y < 0 || y >= width) {
        null
    } else {
        this[y * width + x]
    }
}

fun <T> String.chop(delimiter: String, transform: (from: String) -> T): Pair<T, T> {
    val (from, to) = split(delimiter)

    return transform(from) to transform(to)
}
fun String.sorted() = toSortedSet().joinToString("")

fun time() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time

fun Map<Int, Long>.merge(other: Map<Int, Long>): Map<Int, Long> {
    return (asSequence() + other.asSequence())
        .groupBy({ it.key }, { it.value })
        .mapValues { it.value.sum() }
}

fun Char.toAsciiInt() = Character.getNumericValue(this)

fun <T> Int.repeat(initialState: T, fold: (acc: T) -> T): T {
    return (0 until this).fold(initialState) { acc, _ -> fold(acc) }
}

data class IncorrectAlgorithmException(val actual: String, val expected: String) : Exception("Expected $expected to equal $actual.")
infix fun <T : Number> T.validate(actual: T) {
    if (this.toLong() != actual.toLong()) {
        throw IncorrectAlgorithmException(this.toString(), actual.toString())
    }
}

// Beautifully ugly hacks to make the Day and Advent of Code templates prettier
val <T> ((T) -> Number).number
    get() = this::class.jvmName.last().toAsciiInt()

infix fun <T, U> T.then(block: (answer: T) -> U) = block(this)

context(Day<T>)
fun <T> ((T) -> Number).printAnswer() =
    fun(answer: Number) =
        println("Day $day.${this.number}: $answer")

context(Day<T>, (T) -> Number)
fun <T> NotImplementedError.printNotImplemented() = println("Day $day.$number is not yet implemented")

context(Day<T>, (T) -> Number)
fun <T> IncorrectAlgorithmException.printIncorrectAlgorithm() =
    println("Day $day.$number is incorrect, expected $expected, got $actual")

fun AdventOfCode.printYear() = println("\nAdvent of Code $year")

val Day<*>.actualInput
    get() = File("src/main/kotlin/com/vngrp/aoc$year/input/day$day.txt")
val Day<*>.exampleInput
    get() = File("src/main/kotlin/com/vngrp/aoc$year/input/test-day$day.txt")

fun time(time: String) = parse(time)

context((T) -> Number, Day<T>)
fun <T> solve(input: File): Number = invoke(parse()(input))

val adventOfCodeEditions = listOf(
    AdventOfCode2015,
    AdventOfCode2016,
    AdventOfCode2017,
    AdventOfCode2018,
    AdventOfCode2019,
    AdventOfCode2020,
    AdventOfCode2021,
    AdventOfCode2022,
    AdventOfCode2023,
)