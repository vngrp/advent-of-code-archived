package com.vngrp.aoc2022.puzzles

import com.vngrp.Day
import java.io.File
import com.vngrp.chunked

typealias CalorieInventories = List<List<Int>>

object Day1 : Day<CalorieInventories>(1, 2022) {
    override fun parse(input: File) =
        input
            .readLines()
            .chunked { it.isNotEmpty() }
            .map {
                it.map(String::toInt)
            }

    override fun part1(input: CalorieInventories) = input.maxOf { it.sum() }
    override fun part2(input: CalorieInventories) = input.map { it.sum() }.sortedDescending().take(3).sum()
}
