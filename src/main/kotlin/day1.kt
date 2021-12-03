/**
 * Credits:
 * Learning check: https://github.com/elizarov/AdventOfCode2021/blob/main/src/Day01.kt
 * Puzzle 2: https://github.com/SebastianAigner/advent-of-code-2021/blob/master/src/main/kotlin/Day01.kt
 */
fun main() {
    fun puzzle1(depths: List<Int>): Int {
        return depths
                    .zipWithNext()
                    .count { (prev, current) -> current > prev }
    }
    fun puzzle2(depths: List<Int>): Int {
        return depths
                    .windowed(4) { it[3] > it[0] }
                    .count { it }
    }

    val test = "day1".readTest { it.toInt() }

    check(puzzle1(test) == 7)
    check(puzzle2(test) == 5)

    val depths = "day1".read { it.toInt() }

    println(puzzle1(depths))
    println(puzzle2(depths))
}

