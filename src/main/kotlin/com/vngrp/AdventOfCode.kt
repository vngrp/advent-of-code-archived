package com.vngrp

import com.vngrp.aoc2018.AoC2018
import com.vngrp.aoc2018.puzzles.Day1
import com.vngrp.aoc2021.AoC2021
import java.io.File
import kotlinx.datetime.LocalTime.Companion.parse

fun main() {
    val competitiveRange = parse("05:45:00")..parse("07:00:00")
    val focus = true

    if (time() in competitiveRange || focus) {
        Day1.solve(3, 2)
    } else {
        AoC2018.saveChristmas()
        AoC2021.saveChristmas()
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
        assert(part1(parse(testInput)) == example1)
        println(part1(parse(input)))

        assert(part2(parse(testInput)) == example2)
        println(part2(parse(input)))
    }
}
