package com.vngrp

import java.io.File

abstract class Day<T>(val day: Int = 0, val year: Int = 0) {
    abstract fun parse(input: File): T
    abstract fun part1(input: T): Number
    abstract fun part2(input: T): Number

    fun solve(exampleAnswer1: Number, exampleAnswer2: Number) {
        solve(::part1, exampleAnswer1)
        solve(::part2, exampleAnswer2)
    }

    private fun solve(part: (T) -> Number, exampleAnswer: Number) = try {
        part(parse(exampleInput)) validate exampleAnswer
        part(parse(input)) then part.printAnswer()
    } catch (e: NotImplementedError) {
        part.printNotImplemented()
    } catch (e: IncorrectAlgorithmException) {
        part.printIncorrectAlgorithm(e.expected, e.actual)
    }
}