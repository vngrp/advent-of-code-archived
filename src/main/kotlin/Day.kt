import java.io.File

abstract class Day<T>(val day: Int = 0, val year: Int = 0) {
    abstract fun parse(): (File) -> T
    abstract fun part1(input: T): Number
    abstract fun part2(input: T): Number

    fun solve(exampleAnswer1: Number, exampleAnswer2: Number) {
        solve(::part1, exampleAnswer1)
        solve(::part2, exampleAnswer2)
    }

    private fun solve(part: (T) -> Number, exampleAnswer: Number) = with(part) {
        try {
            solve(exampleInput) validate exampleAnswer
            solve(actualInput) then printAnswer()
        }
        catch (e: NotImplementedError) { e.printNotImplemented() }
        catch (e: IncorrectAlgorithmException) { e.printIncorrectAlgorithm() }
    }
}