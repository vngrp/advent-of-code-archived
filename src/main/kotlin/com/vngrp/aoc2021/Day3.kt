package com.vngrp.aoc2021

import com.vngrp.Day
import java.io.File

typealias Bit = Boolean
typealias BitMap = List<List<Bit>>

fun List<Bit>.toInt(): Int {
    return this
        .map {
            if (it) '1' else '0'
        }
        .joinToString("")
        .let {
            Integer.parseInt(it, 2)
        }
}

object Day3: Day<BitMap>(3, 2021) {
    override fun parse(input: File) = input.readLines().map { it.map { char -> char == '1' } }

    override fun part1(input: BitMap): Number {
        val rowLength = 0 until input.first().size
        val gamma = rowLength.map { mostCommonBit(it, input) }
        val epsilon = gamma.map { !it }

        return gamma.toInt() * epsilon.toInt()
    }

    override fun part2(input: BitMap): Number {
        val oxygenGeneratorRating = findRating(input, Day3::mostCommonBit)
        val co2ScrubberRating = findRating(input, Day3::leastCommonBit)

        return oxygenGeneratorRating * co2ScrubberRating
    }

    private fun mostCommonBit(index: Int, input: BitMap): Bit = !leastCommonBit(index, input)
    private fun leastCommonBit(index: Int, input: BitMap): Bit {
        return input.count { ! it[index] } > input.size.div(2)
    }

    private fun findRating(
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
}

