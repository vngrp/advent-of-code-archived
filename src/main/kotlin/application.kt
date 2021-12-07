import java.io.File


interface Day {
    val examplePuzzle1: Number
    val examplePuzzle2: Number

    fun puzzle1(lines: List<String>): Number
    fun puzzle2(lines: List<String>): Number
}

fun main() {
    val regex = Regex("(day\\d).txt")
    val filenames = File("input/")
                    .walk()
                    .filter { regex.matches(it.name) }
                    .mapNotNull { regex.find(it.name)?.groupValues?.get(1) }

    val days = filenames
                    .toSortedSet()
                    .map { it.uppercaseFirst() }
                    .map { it to evalDay(it) }

    days.forEachIndexed { index, (name, day) ->
        val test = File("input/${name}-test.txt").readLines()

        println("------ Day ${index + 1} ------")

        assert(day.puzzle1(test), day.examplePuzzle1)
        assert(day.puzzle2(test), day.examplePuzzle2)

        val input = File("input/${name}.txt").readLines()

        println(day.puzzle1(input))
        println(day.puzzle2(input))
    }
}