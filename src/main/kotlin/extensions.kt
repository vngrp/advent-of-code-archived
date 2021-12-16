import java.lang.IllegalArgumentException

fun <T : Any> assert(some: T, other: T) {
    if (some != other) {
        throw IllegalArgumentException("Failed to assert that $some equals $other.")
    }
}

fun <T> String.chop(delimiter: String, transform: (from: String) -> T): Pair<T, T> {
    val (from, to) = split(delimiter)

    return transform(from) to transform(to)
}

fun List<Bit>.toInt(): Int {
    return this
        .map {
            if (it) '1' else '0'
        }
        .joinToString("")
        .let {
            Integer.parseInt(it, 2)
        }
}

fun <T> List<T>.replace(old: T, new: T): List<T> {
    val index = indexOf(old)
    if (index < 0) return this

    val list = toMutableList()
    list[index] = new

    return list
}

fun Map<Int, Long>.merge(other: Map<Int, Long>): Map<Int, Long> {
    return (asSequence() + other.asSequence())
        .groupBy({ it.key }, { it.value })
        .mapValues { it.value.sum() }
}

fun List<Int>.min() = minOrNull()!!
fun List<Int>.max() = maxOrNull()!!

fun String.sorted() = toSortedSet().joinToString("")

fun Char.toAsciiInt() = Character.getNumericValue(this)

fun <T> List<T>.getAdjacents(point: Point, width: Int, includeDiagonal: Boolean = false): List<T> {
    val (x, y) = point

    val diagonals = listOfNotNull(
        this.getAt(x - 1, y - 1, width),
        this.getAt(x + 1, y - 1, width),
        this.getAt(x - 1, y + 1, width),
        this.getAt(x + 1, y + 1, width),
    )
    val horizontalVerticals = listOfNotNull(
        this.getAt(x, y - 1, width),
        this.getAt(x - 1, y, width),
        this.getAt(x + 1, y, width),
        this.getAt(x, y + 1, width),
    )

    return if (includeDiagonal) {
        diagonals + horizontalVerticals
    } else {
        diagonals
    }
}

fun <T> List<T>.getAt(x: Int, y: Int, width: Int): T? {
    return if (x < 0 || x >= width || y < 0 || y >= width) {
        null
    } else {
        this[y * width + x]
    }
}

fun <T> Int.repeat(initialState: T, fold: (acc: T) -> T): T {
    return (0 until this).fold(initialState) { acc, _ -> fold(acc) }
}

