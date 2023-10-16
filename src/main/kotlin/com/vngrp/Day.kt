package com.vngrp

import java.io.File

abstract class Day<T>(val day: Int = 0, val year: Int = 0) {
    abstract fun parse(): (File) -> T
    abstract fun part1(input: T): Number
    abstract fun part2(input: T): Number

    fun solve(example1: Number, example2: Number) {
        solve(::part1, example1)
        solve(::part2, example2)
    }

    private fun solve(part: (T) -> Number, example: Number) = with(part, AsciiArtBuilder) {
        try {
            solve(exampleInput) validate example
            solve(actualInput) then answer()
        }
        catch (e: NotImplementedError) { e.printNotImplemented() }
        catch (e: IncorrectAlgorithmException) { e.printIncorrectAlgorithm() }
    }
}
