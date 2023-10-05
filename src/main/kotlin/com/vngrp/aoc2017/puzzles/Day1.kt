package com.vngrp.aoc2017.puzzles

import com.vngrp.Day
import com.vngrp.toAsciiInt
import java.io.File

object Day1 : Day<List<Int>>(1, 2017) {
    override fun parse(input: File) = input.readText().map { it.toAsciiInt() }
    override fun part1(input: List<Int>) = input
        .plus(input.first())
        .zipWithNext()
        .filter { it.first == it.second }
        .sumOf { it.first }

    override fun part2(input: List<Int>) = input.first()
}
