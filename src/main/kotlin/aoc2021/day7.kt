package aoc2021

import Day
import kotlin.math.abs
import max
import min

class Day7: Day {
    override val examplePuzzle1 = 37
    override val examplePuzzle2 = 168

    override fun puzzle1(lines: List<String>): Int {
        val positions = lines.flatMap { it.split(",") }.map { it.toInt() }
        return fuelCost(positions, ::equalFuelCost)
    }

    override fun puzzle2(lines: List<String>): Int {
        val positions = lines.flatMap { it.split(",") }.map { it.toInt() }
        return fuelCost(positions, ::incrementingFuelCost)
    }
}

fun fuelCost(positions: List<Int>, calculate: (position: Int) -> Int): Int {
    val range = positions.min()..positions.max()

    return range.map { base ->
        positions.sumOf { abs(base - it).let(calculate) }
    }.min()
}

fun equalFuelCost(diff: Int): Int = diff
fun incrementingFuelCost(diff: Int): Int = (0..diff).reduce(Int::plus)