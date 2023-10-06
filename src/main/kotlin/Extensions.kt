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
fun File.parseInts() = readLines().map { it.toInt() }
fun File.parseChars() = readText().toCharArray().toList()

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

infix fun Number.then(block: (answer: Number) -> Unit) = block(this)

context(Day<*>)
fun <T>((T) -> Number).printAnswer() = fun(answer: Number) {
    println("Day $day.${this.number}: $answer")
}

context(Day<*>)
fun <T>((T) -> Number).printNotImplemented() = println("Day $day.${this.number} is not yet implemented")

context(Day<*>)
fun <T>((T) -> Number).printIncorrectAlgorithm(expected: String, actual: String) =
    println("Day $day.${this.number} is incorrect, expected $expected, got $actual")

fun AdventOfCode.printYear() = println("\nAdvent of Code $year")

val Day<*>.input
    get() = File("src/main/kotlin/aoc$year/input/day$day.txt")
val Day<*>.exampleInput
    get() = File("src/main/kotlin/aoc$year/input/test-day$day.txt")

fun time(time: String) = parse(time)
