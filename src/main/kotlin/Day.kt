import java.io.File

abstract class Day<T>(val day: Int = 0, val year: Int = 0) {
    abstract fun parse(input: File): T
    abstract fun part1(input: T): Number
    abstract fun part2(input: T): Number

    fun solve(exampleAnswer1: Number, exampleAnswer2: Number) {
        solve(::part1, exampleAnswer1)
        solve(::part2, exampleAnswer2)
    }

    private fun solve(part: (T) -> Number, exampleAnswer: Number) = with(part) {
        try {
            invoke(parse(exampleInput)) validate exampleAnswer
            invoke(parse(input)) then printAnswer()
        }
        catch (e: NotImplementedError) { e.printNotImplemented() }
        catch (e: IncorrectAlgorithmException) { e.printIncorrectAlgorithm() }
    }
}