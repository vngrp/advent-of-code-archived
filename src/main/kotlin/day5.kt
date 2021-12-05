
typealias Point = Pair<Int, Int>
data class Line(val points: List<Point>)

fun main() {
    fun puzzle1(lines: List<Line>): Int =
        lines
            .flatMap { it.points }
            .groupingBy { it }
            .eachCount()
            .filter { it.value >= 2 }
            .count()

    fun puzzle2(lines: List<Line>): Int = puzzle1(lines)

    val test = "day5"
                 .readTest()
                 .let(::parseIntoLines)

    assert(puzzle1(test), 5)
    assert(puzzle2(test), 12)

    val input = "day5".read().let(::parseIntoLines)

    println(puzzle1(input))
    println(puzzle2(input))
}

fun isDiagonalLine(from: Point, to: Point): Boolean = from.first != to.first && from.second != to.second
fun isHorizontalLine(from: Point, to: Point): Boolean = from.second == to.second

fun drawLine(line: Pair<Point, Point>): Line {
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

        var y = y1
        val points = if (y1 > y2) {
            (x1..x2).map { x ->
                Point(x, y--)
            }
        } else {
            (x1..x2).map { x ->
                Point(x, y++)
            }
        }
        return Line(points)
    }

    val (from, to) = line
    return when {
        // isDiagonalLine(from, to) -> drawDiagonalLine(from, to)
        isHorizontalLine(from, to) -> drawHorizontalLine(from.first, to.first, from.second)
        else -> drawVerticalLine(from.second, to.second, from.first)
    }
}

private fun parseIntoLines(input: List<String>): List<Line> {
    return input.map { line ->
        val (left, right) = line.split(" -> ")

        val from = left.chop(",") { it.toInt() }
        val to = right.chop(",") { it.toInt() }

        from to to
        if (to.first > from.first || to.second > from.second) to to from
        else from to to

//        if (isDiagonalLine(from, to)) {
//            if (to.first > from.first) to to from
//            else from to to
//        }
//        else {
//            if (to.first > from.first || to.second > from.second) to to from
//            else from to to
//        }
    }
     .filterNot { (from, to) -> isDiagonalLine(from, to) }
     .map(::drawLine)
}


