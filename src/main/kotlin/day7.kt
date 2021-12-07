import java.io.File
import kotlin.math.abs

fun main() {
    fun puzzle1(positions: List<Int>): Int {
        return fuelCost(positions, ::equalFuelCost)
    }

    fun puzzle2(positions: List<Int>): Int {
        return fuelCost(positions, ::incrementingFuelCost)
    }

    val test = File("input/day7-test.txt").readText().split(",").map { it.toInt() }

    assert(puzzle1(test), 37)
    assert(puzzle2(test), 168)

    val input = File("input/day7.txt").readText().split(",").map { it.toInt() }

    println(puzzle1(input))
    println(puzzle2(input))
}

fun fuelCost(positions: List<Int>, calculate: (position: Int) -> Int): Int {
    val range = positions.min()..positions.max()

    return range.map { base ->
        positions.sumOf { abs(base - it).let(calculate) }
    }.min()
}

fun equalFuelCost(diff: Int): Int = diff
fun incrementingFuelCost(diff: Int): Int = (0..diff).reduce(Int::plus)