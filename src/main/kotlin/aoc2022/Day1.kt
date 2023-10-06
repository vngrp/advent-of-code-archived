package aoc2022

import Day
import chunked
import java.io.File

typealias CalorieInventories = List<List<Int>>

object Day1 : Day<CalorieInventories>(1, 2022) {
    override fun parse(input: File) =
        input
            .readLines()
            .chunked { it.isNotEmpty() }
            .map { it.map(String::toInt) }

    override fun part1(input: CalorieInventories) = input.maxOf { it.sum() }
    override fun part2(input: CalorieInventories) = input.map { it.sum() }.sortedDescending().take(3).sum()
}