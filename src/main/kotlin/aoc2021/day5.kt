package aoc2021

import Day
import chop
import java.io.File

data class Point(val x: Int, val y: Int) {
    constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)
}
data class Line(val points: List<Point>)

/**
 * Credits:
 * Print a grid: https://github.com/SebastianAigner/advent-of-code-2021/blob/master/src/main/kotlin/Day05.kt
 */
class Day5: Day {
    override val examplePuzzle1 = 5
    override val examplePuzzle2 = 12

    override fun puzzle1(lines: List<String>): Int {
        return lines
            .let(::parseIntoLines)
            .filterNot(::isDiagonalLine)
            .flatMap { it.points }
            .groupingBy { it }
            .eachCount()
            .filter { it.value >= 2 }
            .count()
    }

    override fun puzzle2(lines: List<String>): Int {
        return lines
            .let(::parseIntoLines)
            .flatMap { it.points }
            .groupingBy { it }
            .eachCount()
            .also(::saveGrid)
            .filter { it.value >= 2 }
            .count()
    }
}

fun isDiagonalLine(line: Line): Boolean {
    val from = line.points.first()
    val to = line.points.last()

    return from.x != to.x && from.y != to.y
}

fun isHorizontalLine(from: Point, to: Point): Boolean = from.y == to.y
fun isVerticalLine(from: Point, to: Point): Boolean = from.x == to.x

fun drawLine(from: Point, to: Point): Line {
    fun drawHorizontalLine(from: Int, to: Int, y: Int): Line {
        val range = if (to > from) (from..to) else (to..from)
        val points = range.map { x -> Point(x, y) }

        return Line(points)
    }
    fun drawVerticalLine(from: Int, to: Int, x: Int): Line {
        val range = if (to > from) (from..to) else (to..from)
        val points = range.map { y -> Point(x, y) }

        return Line(points)
    }
    fun drawDiagonalLine(from: Point, to: Point): Line {
        val (x1, y1) = from
        val (x2, y2) = to

        /**
         * 0, 0 -> 8, 8
         * 8, 0 -> 0, 8
         * 0, 8 -> 8, 0
         * 8, 8 -> 0, 0
         */
        val points = when {
            x1 < x2 && y1 < y2 -> (x1..x2).mapIndexed { i, x -> Point(x, y1 + i) }
            x1 > x2 && y1 < y2 -> (x1.downTo(x2)).mapIndexed { i, x -> Point(x, y1 + i) }
            x1 < x2 && y1 > y2 -> (x1..x2).mapIndexed { i, x -> Point(x, y1 - i) }
            x1 > x2 && y1 > y2 -> (x1.downTo(x2)).mapIndexed { i, x -> Point(x, y1 - i) }
            else -> throw Error("Horizontal or vertical line drawn as diagonal.")
        }

        return Line(points)
    }

    return when {
        isVerticalLine(from, to) -> drawVerticalLine(from.y, to.y, from.x)
        isHorizontalLine(from, to) -> drawHorizontalLine(from.x, to.x, from.y)
        else -> drawDiagonalLine(from, to)
    }
}

private fun parseIntoLines(input: List<String>): List<Line> {
    return input.map { line ->
        val (left, right) = line.split(" -> ")

        val from = left.chop(",") { it.toInt() }
        val to = right.chop(",") { it.toInt() }

        drawLine(Point(from), Point(to))
    }
}

private fun drawGrid(points: Map<Point, Int>): String {
    val maxWidth = points.keys.maxOf { it.x } + 1
    val maxHeight = points.keys.maxOf { it.y } + 1

    val grid = Array(maxHeight) {
        Array(maxWidth) {
            '.'
        }
    }

    for ((point, count) in points) {
        grid[point.y][point.x] = (count + 48).toChar() // Ascii
    }

    return grid.joinToString("\n") {
        it.joinToString("")
    }
}

private fun saveGrid(points: Map<Point, Int>) {
    // println(drawGrid(points))
    File("output/day5.txt").writeText(drawGrid(points))
}

