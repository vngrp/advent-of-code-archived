package com.vngrp.aoc2017

import com.vngrp.Day
import com.vngrp.parseInts

object Day1 : Day<List<Int>>(1, 2017) {
    override fun parse() = ::parseInts
    override fun part1(input: List<Int>) = input
        .plus(input.first())
        .zipWithNext()
        .filter { it.first == it.second }
        .sumOf { it.first }

    override fun part2(input: List<Int>) = throw NotImplementedError()
}
