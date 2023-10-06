package aoc2022

import Day
import chunked
import java.io.File
import parseGroups
import parseInts

typealias CalorieInventories = List<List<Int>>

object Day1 : Day<CalorieInventories>(1, 2022) {
    override fun parse() = ::parseInventories

    override fun part1(input: CalorieInventories) = input.maxOf { it.sum() }
    override fun part2(input: CalorieInventories) = input.map { it.sum() }.sortedDescending().take(3).sum()

    private fun parseInventories(file: File) =
        parseGroups(file, String::isNotEmpty)
            .map { it.parseInts() }

}