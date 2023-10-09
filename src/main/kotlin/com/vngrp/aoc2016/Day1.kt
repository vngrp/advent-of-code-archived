package com.vngrp.aoc2016

import com.vngrp.Cardinal
import com.vngrp.Day
import com.vngrp.Direction
import com.vngrp.Point
import com.vngrp.component1
import com.vngrp.component2
import com.vngrp.toAsciiInt
import com.vngrp.toDirection
import java.io.File
import kotlin.math.abs

data class Instruction(val direction: Direction, val distance: Int)

object Day1 : Day<List<Instruction>>(1, 2016) {
    override fun parse() = ::parseInstructions
    override fun part1(input: List<Instruction>) = input.fold(Point(0, 0, Cardinal.NORTH))  { point, instruction ->
        point.move(
            point.orientation,
            instruction.direction,
            instruction.distance
        )
    }.let {
        abs(it.x) + abs(it.y)
    }
    
    override fun part2(input: List<Instruction>): Number {
        TODO("Not yet implemented")
    }

    private fun parseInstructions(file: File) = file.readText().split(", ").map {
        val (direction, distance) = it
        Instruction(direction.toDirection(), distance.toAsciiInt())
    }
}
