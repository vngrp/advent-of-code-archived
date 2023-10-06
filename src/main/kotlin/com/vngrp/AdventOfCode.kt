package com.vngrp

import com.vngrp.aoc2015.AdventOfCode2015
import com.vngrp.aoc2015.Day1
import com.vngrp.aoc2017.AdventOfCode2017
import com.vngrp.aoc2018.AdventOfCode2018
import com.vngrp.aoc2021.AdventOfCode2021
import com.vngrp.aoc2022.AdventOfCode2022

fun main() {
    val competitiveRange = time("05:45:00")..time("07:00:00")
    val focus = true

    if (time() in competitiveRange || focus) {
        Day1.solve(-3, 45000)
    } else {
        adventOfCodeEditions.forEach {
            it.printYear()
            it.saveChristmas()
        }
    }
}

interface AdventOfCode {
    val year: Int
    fun saveChristmas()
    fun printYear() = println("\nAdvent of Code $year")
}

val adventOfCodeEditions = listOf(
    AdventOfCode2015,
    AdventOfCode2017,
    AdventOfCode2018,
    AdventOfCode2021,
    AdventOfCode2022
)