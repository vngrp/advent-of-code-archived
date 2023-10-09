package com.vngrp.aoc2016

import com.vngrp.Day
import com.vngrp.Point
import com.vngrp.component1
import com.vngrp.component2
import com.vngrp.toAsciiInt
import java.io.File
import kotlin.math.abs

data class Instruction(val direction: Direction, val distance: Int)
enum class Orientation { UP, DOWN, LEFT, RIGHT }
enum class Direction { LEFT, RIGHT }

object Day1 : Day<List<Instruction>>(1, 2016) {
    override fun parse() = Day1::parseInstructions
    override fun part1(input: List<Instruction>) = input.fold(Point(0, 0) to Orientation.UP)  { (point, orientation), instruction ->
        when (orientation) {
            Orientation.UP -> when (instruction.direction) {
                Direction.LEFT -> Point(point.x - instruction.distance, point.y) to Orientation.LEFT
                Direction.RIGHT -> Point(point.x + instruction.distance, point.y) to Orientation.RIGHT
            }
            Orientation.DOWN -> when (instruction.direction) {
                Direction.LEFT -> Point(point.x + instruction.distance, point.y) to Orientation.RIGHT
                Direction.RIGHT -> Point(point.x - instruction.distance, point.y) to Orientation.LEFT
            }
            Orientation.LEFT -> when (instruction.direction) {
                Direction.LEFT -> Point(point.x, point.y + instruction.distance) to Orientation.DOWN
                Direction.RIGHT -> Point(point.x, point.y - instruction.distance) to Orientation.UP
            }
            Orientation.RIGHT -> when (instruction.direction) {
                Direction.LEFT -> Point(point.x, point.y - instruction.distance) to Orientation.UP
                Direction.RIGHT -> Point(point.x, point.y + instruction.distance) to Orientation.DOWN
            }
        }
    }.let { (point, _) -> point }.let { (x, y) -> abs(x) + abs(y) }

    override fun part2(input: List<Instruction>): Number {
        TODO("Not yet implemented")
    }

    private fun parseInstructions(file: File) = file.readText().split(", ").map {
        val (direction, distance) = it
        Instruction(direction.toDirection(), distance.toAsciiInt())
    }

    private fun Char.toDirection() = when (this) {
        'L' -> Direction.LEFT
        'R' -> Direction.RIGHT
        else -> throw IllegalArgumentException("Invalid direction: $this")
    }
}
