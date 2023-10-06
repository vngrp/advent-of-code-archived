package com.vngrp

import com.vngrp.aoc2015.AdventOfCode2015
import com.vngrp.aoc2016.AdventOfCode2016
import com.vngrp.aoc2017.AdventOfCode2017
import com.vngrp.aoc2018.AdventOfCode2018
import com.vngrp.aoc2019.AdventOfCode2019
import com.vngrp.aoc2020.AdventOfCode2020
import com.vngrp.aoc2021.AdventOfCode2021
import com.vngrp.aoc2022.AdventOfCode2022
import com.vngrp.aoc2023.AdventOfCode2023

fun main() {
    val competitiveRange = time("05:45:00")..time("07:00:00")
    val inFocusMode = false

    if (time() in competitiveRange || inFocusMode) {
        // Quickly do current day
        // Day1.solve(0, 0)
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
}

val adventOfCodeEditions = listOf(
    AdventOfCode2015,
    AdventOfCode2016,
    AdventOfCode2017,
    AdventOfCode2018,
    AdventOfCode2019,
    AdventOfCode2020,
    AdventOfCode2021,
    AdventOfCode2022,
    AdventOfCode2023,
)