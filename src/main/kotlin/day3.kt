
typealias Bit = Boolean
typealias BitMap = List<List<Bit>>

fun main() {
    fun puzzle1(input: BitMap): Int {
        val rowLength = 0 until input.first().size
        val gamma = rowLength.map { mostCommonBit(it, input) }
        val epsilon = gamma.map { !it }

        return gamma.toInt() * epsilon.toInt()
    }

    fun puzzle2(input: BitMap): Int {
        val oxygenGeneratorRating = findRating(input, ::mostCommonBit)
        val co2ScrubberRating = findRating(input, ::leastCommonBit)

        return oxygenGeneratorRating * co2ScrubberRating
    }

    val test = "day3".readTest { it.map { char -> char == '1' } }

    assert(puzzle1(test), 198)
    assert(puzzle2(test), 230)

    val input = "day3".read { it.map { char -> char == '1' } }

    println(puzzle1(input))
    println(puzzle2(input))
}

fun mostCommonBit(index: Int, input: BitMap): Bit = !leastCommonBit(index, input)
fun leastCommonBit(index: Int, input: BitMap): Bit {
    return input.count { ! it[index] } > input.size.div(2)
}

fun findRating(
    input: BitMap,
    block: (index: Int, input: BitMap) -> Bit,
    index: Int = 0
): Int {
    if (input.size == 1) return input.first().toInt()
    if (input.isEmpty()) throw IllegalArgumentException("Could not find the one.")

    val bit = block(index, input)
    val remaining = input.filter { it[index] == bit }

    return findRating(remaining, block, index + 1)
}