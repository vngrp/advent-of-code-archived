/**
 * Credits:
 * Learning check: https://github.com/elizarov/AdventOfCode2021/blob/main/src/Day01.kt
 * Puzzle 2: https://github.com/SebastianAigner/advent-of-code-2021/blob/master/src/main/kotlin/Day01.kt
 */
class Day1: Day {
    override val examplePuzzle1 = 7
    override val examplePuzzle2 = 5

    override fun puzzle1(lines: List<String>): Int {
        return lines
                .map(String::toInt)
                .zipWithNext()
                .count { (prev, current) -> current > prev }
    }

    override fun puzzle2(lines: List<String>): Int {
        return lines
                .map(String::toInt)
                .windowed(4) { it[3] > it[0] }
                .count { it }
    }
}