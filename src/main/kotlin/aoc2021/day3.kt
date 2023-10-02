package aoc2021

import Day
import toInt

typealias Bit = Boolean
typealias BitMap = List<List<Bit>>

class Day3: Day {
    override val examplePuzzle1 = 198
    override val examplePuzzle2 = 230

    override fun puzzle1(lines: List<String>): Int {
        val input = lines.map { it.map { char -> char == '1' } }
        val rowLength = 0 until input.first().size
        val gamma = rowLength.map { mostCommonBit(it, input) }
        val epsilon = gamma.map { !it }

        return gamma.toInt() * epsilon.toInt()
    }

    override fun puzzle2(lines: List<String>): Int {
        val input = lines.map { it.map { char -> char == '1' } }
        val oxygenGeneratorRating = findRating(input, ::mostCommonBit)
        val co2ScrubberRating = findRating(input, ::leastCommonBit)

        return oxygenGeneratorRating * co2ScrubberRating
    }
}

fun mostCommonBit(index: Int, input: BitMap): Bit = !leastCommonBit(index, input)
fun leastCommonBit(index: Int, input: BitMap): Bit {
    return input.count { ! it[index] } > input.size.div(2)
}

fun findRating(
    input: BitMap,
    block: (index: Int, input: BitMap) -> Bit,
    index: Int = 0
): Int {
    if (input.size == 1) return input.first().toInt()
    if (input.isEmpty()) throw IllegalArgumentException("Could not find the one.")

    val bit = block(index, input)
    val remaining = input.filter { it[index] == bit }

    return findRating(remaining, block, index + 1)
}