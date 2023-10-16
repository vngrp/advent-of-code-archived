package com.vngrp

import arrow.fx.coroutines.parMap
import com.vngrp.aoc2016.Day1
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val competitiveRange = time("05:45:00")..time("07:00:00")
    val inFocusMode = true

    if (time() in competitiveRange || inFocusMode) {
        Day1.solve(12, 0)
    } else {
        with(AsciiArtBuilder) {
            adventOfCodeEditions.parMap {
                it.saveChristmas()
            }
        }
    }
}

interface AdventOfCode {
    val year: Int

    fun saveChristmas() = printYear().also { solvePuzzles() }
    fun solvePuzzles()
}
