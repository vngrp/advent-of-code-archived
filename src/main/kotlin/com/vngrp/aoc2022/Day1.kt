package com.vngrp.aoc2022

import com.vngrp.Day
import java.io.File
import com.vngrp.parseGroups
import com.vngrp.parseInts

typealias CalorieInventories = List<List<Int>>

object Day1 : Day<CalorieInventories>(1, 2022) {
    override fun parse() = Day1::parseInventories

    override fun part1(input: CalorieInventories) = input.maxOf { it.sum() }
    override fun part2(input: CalorieInventories) = input.map { it.sum() }.sortedDescending().take(3).sum()

    private fun parseInventories(file: File) =
        parseGroups(file, String::isNotEmpty)
            .map { it.parseInts() }

}