import java.io.File

typealias Point = Pair<Int, Int>
data class Line(val points: List<Point>)

/**
 * Credits:
 * Print a grid: https://github.com/SebastianAigner/advent-of-code-2021/blob/master/src/main/kotlin/Day05.kt
 */
fun main() {
    fun puzzle1(lines: List<Line>): Int {
        return lines
            .flatMap { it.points }
            .groupingBy { it }
            .eachCount()
            .also(::printAndSaveGrid)
            .filter { it.value >= 2 }
            .count()
    }

    fun puzzle2(lines: List<Line>): Int = puzzle1(lines)

    val test2 = "day5".readTest().let(::parseIntoLines)
    val test1 = test2.filterNot(::isDiagonalLine)

    assert(puzzle1(test1), 5)
    assert(puzzle2(test2), 12)

    val input2 = "day5".read().let(::parseIntoLines)
    val input1 = input2.filterNot(::isDiagonalLine)

    println(puzzle1(input1))
    println(puzzle2(input2))
}

fun isDiagonalLine(line: Line): Boolean {
    val from = line.points.first()
    val to = line.points.last()

    return from.first != to.first && from.second != to.second
}

fun isHorizontalLine(from: Point, to: Point): Boolean = from.second == to.second
fun isVerticalLine(from: Point, to: Point): Boolean = from.first == to.first

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
            x1 < x2 && y1 < y2 -> (x1..x2).mapIndexed { i, x -> Point(x, y1 + i)}
            x1 > x2 && y1 < y2 -> (x1.downTo(x2)).mapIndexed { i, x -> Point(x, y1 + i)}
            x1 < x2 && y1 > y2 -> (x1..x2).mapIndexed { i, x -> Point(x, y1 - i)}
            x1 > x2 && y1 > y2 -> (x1.downTo(x2)).mapIndexed { i, x -> Point(x, y1 - i)}
            else -> throw Error("Horizontal or vertical line drawn as diagonal.")
        }

        return Line(points)
    }

    return when {
        isVerticalLine(from, to) -> drawVerticalLine(from.second, to.second, from.first)
        isHorizontalLine(from, to) -> drawHorizontalLine(from.first, to.first, from.second)
        else -> drawDiagonalLine(from, to)
    }
}

private fun parseIntoLines(input: List<String>): List<Line> {
    return input.map { line ->
        val (left, right) = line.split(" -> ")

        val from = left.chop(",") { it.toInt() }
        val to = right.chop(",") { it.toInt() }

        drawLine(from, to)
    }
}

private fun drawGrid(points: Map<Point, Int>): String {
    val grid = Array(points.keys.maxOf { it.second } + 1) {
        Array(points.keys.maxOf { it.first } + 1) {
            '.'
        }
    }

    for ((point, count) in points) {
        grid[point.second][point.first] = (count + 48).toChar() // Ascii
    }

    return grid.joinToString("\n") {
        it.joinToString("")
    }
}

private fun printAndSaveGrid(points: Map<Point, Int>) {
    val grid = drawGrid(points)

    println(grid)
    File("output/day5.txt").writeText(drawGrid(points))
}

