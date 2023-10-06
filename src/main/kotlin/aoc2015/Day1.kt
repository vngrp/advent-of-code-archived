package aoc2015

import Day
import parseChars
import java.io.File

typealias Floors = List<Char>

object Day1 : Day<Floors>(1, 2015) {
    override fun parse() = ::parseChars
    override fun part1(input: Floors) = input.sumOf {
        when (it) {
            '(' -> 1L
            ')' -> -1L
            else -> throw Error("Invalid character: $it")
        }
    }

    override fun part2(input: Floors) = TODO()
}