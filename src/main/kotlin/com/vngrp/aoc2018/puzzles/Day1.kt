package com.vngrp.aoc2018.puzzles

import com.vngrp.Day
import java.io.File
import com.vngrp.parseInts

object Day1 : Day<List<Int>>(1, 2018) {
    override fun parse(input: File) = input.parseInts()
    override fun part1(input: List<Int>) = input.sum()
    override fun part2(input: List<Int>) = findDuplicate(input)

    private fun findDuplicate(input: List<Int>): Int {
        val seen = mutableSetOf<Int>()
        var sum = 0
        while (true) {
            for (number in input) {
                sum += number
                if (sum in seen) return sum
                seen.add(sum)
            }
        }
    }
}