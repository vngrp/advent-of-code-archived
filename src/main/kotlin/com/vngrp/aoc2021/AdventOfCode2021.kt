package com.vngrp.aoc2021

import com.vngrp.AdventOfCode

object AdventOfCode2021 : AdventOfCode {
    override val year = 2021
    override fun saveChristmas() {
        Day1.solve(7, 5)
        Day2.solve(150, 900)
    }
}