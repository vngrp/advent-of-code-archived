import java.io.File
import java.time.LocalDateTime
import java.time.LocalTime

interface Day {
    val examplePuzzle1: Number
    val examplePuzzle2: Number

    fun puzzle1(lines: List<String>): Number
    fun puzzle2(lines: List<String>): Number
}

interface GDay<P> {
    val examplePuzzle1: Number
    val examplePuzzle2: Number

    fun puzzle1(input: P): Number
    fun puzzle2(input: P): Number
    fun parseInput(file: File): P
}

fun main() {
    val competitiveRange = LocalTime.parse("05:45:00") to LocalTime.parse("07:00:00")
    val now = LocalDateTime.now().toLocalTime()

    if (now.isAfter(competitiveRange.first) && now.isBefore(competitiveRange.second)) {
        println("------ Day 11 ------")

        val day11 = Day11()
        val test11 = File("input/day11-test.txt")

        assert(day11.puzzle1(day11.parseInput(test11)), day11.examplePuzzle1)
        assert(day11.puzzle2(day11.parseInput(test11)), day11.examplePuzzle2)

        val input11 = File("input/day11.txt")

        println(day11.puzzle1(day11.parseInput(input11)))
        println(day11.puzzle2(day11.parseInput(input11)))
    } else {
        println("------ Day 1 ------")

        val day1 = Day1()
        val test1 = File("input/day1-test.txt").readLines()

        assert(day1.puzzle1(test1), day1.examplePuzzle1)
        assert(day1.puzzle2(test1), day1.examplePuzzle2)

        val input1 = File("input/day1.txt").readLines()

        println(day1.puzzle1(input1))
        println(day1.puzzle2(input1))

        println("------ Day 2 ------")

        val day2 = Day2()
        val test2 = File("input/day2-test.txt").readLines()

        assert(day2.puzzle1(test2), day2.examplePuzzle1)
        assert(day2.puzzle2(test2), day2.examplePuzzle2)

        val input2 = File("input/day2.txt").readLines()

        println(day2.puzzle1(input2))
        println(day2.puzzle2(input2))

        println("------ Day 3 ------")

        val day3 = Day3()
        val test3 = File("input/day3-test.txt").readLines()

        assert(day3.puzzle1(test3), day3.examplePuzzle1)
        assert(day3.puzzle2(test3), day3.examplePuzzle2)

        val input3 = File("input/day3.txt").readLines()

        println(day3.puzzle1(input3))
        println(day3.puzzle2(input3))

        println("------ Day 4 ------")

        val day4 = Day4()
        val test4 = File("input/day4-test.txt").readLines()

        assert(day4.puzzle1(test4), day4.examplePuzzle1)
        assert(day4.puzzle2(test4), day4.examplePuzzle2)

        val input4 = File("input/day4.txt").readLines()

        println(day4.puzzle1(input4))
        println(day4.puzzle2(input4))

        println("------ Day 5 ------")

        val day5 = Day5()
        val test5 = File("input/day5-test.txt").readLines()

        assert(day5.puzzle1(test5), day5.examplePuzzle1)
        assert(day5.puzzle2(test5), day5.examplePuzzle2)

        val input5 = File("input/day5.txt").readLines()

        println(day5.puzzle1(input5))
        println(day5.puzzle2(input5))

        println("------ Day 6 ------")

        val day6 = Day6()
        val test6 = File("input/day6-test.txt").readLines()

        assert(day6.puzzle1(test6), day6.examplePuzzle1)
        assert(day6.puzzle2(test6), day6.examplePuzzle2)

        val input6 = File("input/day6.txt").readLines()

        println(day6.puzzle1(input6))
        println(day6.puzzle2(input6))

        println("------ Day 7 ------")

        val day7 = Day7()
        val test7 = File("input/day7-test.txt").readLines()

        assert(day7.puzzle1(test7), day7.examplePuzzle1)
        assert(day7.puzzle2(test7), day7.examplePuzzle2)

        val input7 = File("input/day7.txt").readLines()

        println(day7.puzzle1(input7))
        println(day7.puzzle2(input7))

        println("------ Day 8 ------")

        val day8 = Day8()
        val test8 = File("input/day8-test.txt").readLines()

        assert(day8.puzzle1(test8), day8.examplePuzzle1)
        assert(day8.puzzle2(test8), day8.examplePuzzle2)

        val input8 = File("input/day8.txt").readLines()

        println(day8.puzzle1(input8))
        println(day8.puzzle2(input8))

        println("------ Day 9 ------")

        val day9 = Day9()
        val test9 = File("input/day9-test.txt").readLines()

        assert(day9.puzzle1(test9), day9.examplePuzzle1)
        assert(day9.puzzle2(test9), day9.examplePuzzle2)

        val input9 = File("input/day9.txt").readLines()

        println(day9.puzzle1(input9))
        println(day9.puzzle2(input9))

        println("------ Day 10 ------")

        val day10 = Day10()
        val test10 = File("input/day10-test.txt").readLines()

        assert(day10.puzzle1(test10), day10.examplePuzzle1)
        assert(day10.puzzle2(test10), day10.examplePuzzle2)

        val input10 = File("input/day10.txt").readLines()

        println(day10.puzzle1(input10))
        println(day10.puzzle2(input10))

        println("------ Day 11 ------")

        val day11 = Day11()
        val test11 = File("input/day11-test.txt")

        assert(day11.puzzle1(day11.parseInput(test11)), day11.examplePuzzle1)
        assert(day11.puzzle2(day11.parseInput(test11)), day11.examplePuzzle2)

        val input11 = File("input/day11.txt")

        println(day11.puzzle1(day11.parseInput(input11)))
        println(day11.puzzle2(day11.parseInput(input11)))
    }


}