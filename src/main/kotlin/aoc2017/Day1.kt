package aoc2017

import Day
import toAsciiInt
import java.io.File
import parseInts

object Day1 : Day<List<Int>>(1, 2017) {
    override fun parse() = ::parseInts
    override fun part1(input: List<Int>) = input
        .plus(input.first())
        .zipWithNext()
        .filter { it.first == it.second }
        .sumOf { it.first }

    override fun part2(input: List<Int>) = throw NotImplementedError()
}
