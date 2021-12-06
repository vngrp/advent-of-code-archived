
fun main() {
    fun puzzle1(input: List<Int>): Int {
        return (1..80).fold(input) { acc, _ ->
            acc.flatMap(::reproduce)
        }.size
    }

    fun puzzle2(input: List<Int>): Long {
        val map = input.groupingBy { it }
                       .eachCount()
                       .mapValues { it.value.toLong() }

        return (1..256).fold(map) { acc, _ ->
            acc
                .map(::reproduceCount)
                .reduce { map1, map2 -> map1.merge(map2) }
        }
            .values
            .fold(0L) { acc, value ->
                acc + value
            }
    }

    val test = "day6".readTest { it.split(",") }.flatten().map { it.toInt() }

    assert(puzzle1(test), 5934)
    assert(puzzle2(test), 26984457539)

    val input = "day6".read() { it.split(",") }.flatten().map { it.toInt() }

    println(puzzle1(input))
    println(puzzle2(input))
}

fun reproduce(fish: Int): List<Int> {
    return if (fish == 0) {
        listOf(6, 8)
    } else {
        listOf(fish - 1)
    }
}

fun reproduceCount(daysToReproduce: Map.Entry<Int, Long>): Map<Int, Long> {
    return if (daysToReproduce.key == 0) {
        mapOf(
            6 to daysToReproduce.value,
            8 to daysToReproduce.value
        )
    } else {
        mapOf(
            daysToReproduce.key - 1 to daysToReproduce.value
        )
    }
}

