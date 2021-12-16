import java.io.File
import kotlin.properties.Delegates

class Day13: GDay<Pair<List<Point>, List<Fold>>> {
    override val examplePuzzle1 = 0
    override val examplePuzzle2 = 0

    override fun puzzle1(input: Pair<List<Point>, List<Fold>>): Number {
        val screen = Array(findHighestY(input.first)) {
            Array(findHighestX(input.first)) {
                '.'
            }
        }

        println(screen)
        TODO("Not yet implemented")
    }

    override fun puzzle2(input: Pair<List<Point>, List<Fold>>): Number {
        TODO("Not yet implemented")
    }

    override fun parseInput(file: File): Pair<List<Point>, List<Fold>> {
        val input = file.readText()
        val coordinates = Regex("(?<x>\\d+),(?<y>\\d+)")
                            .findAll(input)
                            .map {
                                Point(
                                    x = it.groups["x"]!!.value.toInt(),
                                    y = it.groups["y"]!!.value.toInt()
                                )
                            }
        val folds = Regex("fold along (?<axis>[xy])=(?<middle>\\d+)")
                            .findAll(input)
                            .map {
                                Fold(
                                    axis = it.groups["axis"]!!.value,
                                    middle = it.groups["middle"]!!.value.toInt()
                                )
                            }

        return coordinates.toList() to folds.toList()
    }

    private fun findHighestX(points: List<Point>): Int = points.maxOf { it.x }
    private fun findHighestY(points: List<Point>): Int = points.maxOf { it.y }
}

data class Fold(val axis: String, val middle: Int)
