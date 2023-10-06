package com.vngrp.aoc2015

import com.vngrp.Day
import com.vngrp.parseChars
import java.io.File

object Day1 : Day<List<Char>>(1, 2015) {
    override fun parse(input: File) = input.parseChars()
    override fun part1(input: List<Char>) = input.sumOf {
        when (it) {
            '(' -> 1L
            ')' -> -1L
            else -> throw Error("Invalid character: $it")
        }
    }

    override fun part2(input: List<Char>) = TODO()
}