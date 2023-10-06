package com.vngrp

import com.vngrp.aoc2017.AoC2017
import com.vngrp.aoc2018.AoC2018
import com.vngrp.aoc2021.AoC2021
import com.vngrp.aoc2022.AoC2022
import com.vngrp.aoc2022.puzzles.Day1
import java.io.File
import kotlinx.datetime.LocalTime.Companion.parse

fun main() {
    val competitiveRange = parse("05:45:00")..parse("07:00:00")
    val focus = true

    if (time() in competitiveRange || focus) {
        Day1.solve(24000, 45000)
    } else {
        AoC2017.saveChristmas()
        AoC2018.saveChristmas()
        AoC2021.saveChristmas()
        AoC2022.saveChristmas()
        // Print the Ascii arts like on the website
//        AdventOfCode::class
//            .editions
//            .forEach {
//                it.saveChristmas()
//            }
    }
}

sealed interface AdventOfCode {
    fun saveChristmas()
}

interface AdventOfCode2015 : AdventOfCode
interface AdventOfCode2016 : AdventOfCode
interface AdventOfCode2017 : AdventOfCode
interface AdventOfCode2018 : AdventOfCode
interface AdventOfCode2019 : AdventOfCode
interface AdventOfCode2020 : AdventOfCode
interface AdventOfCode2021 : AdventOfCode
interface AdventOfCode2022 : AdventOfCode
interface AdventOfCode2023 : AdventOfCode

abstract class Day<T>(val day: Int = 0, val year: Int = 0) {
    abstract fun parse(input: File): T
    abstract fun part1(input: T): Number
    abstract fun part2(input: T): Number

    fun solve(example1: Number, example2: Number) {
        try {
            assert(part1(parse(testInput)), example1)
            part1(parse(input))
                .also { println("Part 1 answer: $it") }
        } catch (e: NotImplementedError) {
            println("Part 1 not yet implemented")
        } catch (e: IncorrectAlgorithmException) {
            println("Part 1 is incorrect, expected ${e.expected}, got ${e.actual}")
        }

        try {
            assert(part2(parse(testInput)), example2)
            part2(parse(input))
                .also { println("Part 2 answer: $it") }
        } catch (e: NotImplementedError) {
            println("Part 2 not yet implemented")
        } catch (e: IncorrectAlgorithmException) {
            println("Part 2 is incorrect, expected ${e.expected}, got ${e.actual}")
        }
    }
}
