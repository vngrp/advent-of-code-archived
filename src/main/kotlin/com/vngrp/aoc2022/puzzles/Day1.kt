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

    override fun part1(input: CalorieInventories): Number {
        println(input)

        return 5
    }

    override fun part2(input: CalorieInventories): Number {
        TODO("Not yet implemented")
    }
}
