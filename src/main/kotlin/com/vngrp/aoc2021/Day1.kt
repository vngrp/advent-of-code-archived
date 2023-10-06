package com.vngrp.aoc2021

import com.vngrp.Day
import java.io.File
import com.vngrp.parseInts

/**
 * Credits:
 * Learning check: https://github.com/elizarov/AdventOfCode2021/blob/main/src/Day01.kt
 * Puzzle 2: https://github.com/SebastianAigner/advent-of-code-2021/blob/master/src/main/kotlin/Day01.kt
 */
object Day1: Day<List<Int>>(1, 2021) {
    override fun parse(input: File) = input.parseInts()

    override fun part1(input: List<Int>) = input
        .zipWithNext()
        .count { (previous, current) -> current > previous }

    override fun part2(input: List<Int>) = input
        .windowed(4) { it[3] > it[0] }
        .count { it }
}