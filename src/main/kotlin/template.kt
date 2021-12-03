
fun main() {
    fun puzzle1(input: List<String>): Int {
        return 5
    }
    fun puzzle2(input: List<String>): Int {
        return 5
    }

    val test = "day".readTest()

    assert(puzzle1(test), 5)
    assert(puzzle2(test), 5)

    val input = "day".read()

    println(puzzle1(input))
    println(puzzle2(input))
}

