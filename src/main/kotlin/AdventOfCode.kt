import java.io.File
import java.time.LocalDateTime
import java.time.LocalTime

fun main() {
    val competitiveRange = LocalTime.parse("05:45:00") to LocalTime.parse("07:00:00")

    if (LocalDateTime.now().toLocalTime() in competitiveRange) {
        process()
    } else {
        //processAllDays()
    }
}

private fun process() {
    with(Year2021Day1()) {
        val test = File("input/day1-test.txt")
        assert(part1(parse(test)), example1)
        assert(part2(parse(test)), example2)

        val input = File("input/day1.txt")
        println(part1(parse(input)))
        println(part2(parse(input)))
    }
}

sealed interface Day<T> {
    val example1: Number
    val example2: Number

    fun parse(input: File): T
    fun part1(input: T): Number
    fun part2(input: T): Number
}

class Year2021Day1(
    override val example1: Number = 0,
    override val example2: Number = 0
) : Day<List<String>> {
    override fun parse(input: File) = input.readLines()

    override fun part1(input: List<String>): Number {
        return 1
    }

    override fun part2(input: List<String>): Number {
        return 0L
    }
}