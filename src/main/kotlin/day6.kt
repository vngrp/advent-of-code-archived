
class Day6: Day {
    override val examplePuzzle1 = 5934
    override val examplePuzzle2 = 26984457539

    override fun puzzle1(lines: List<String>): Int {
        val input = lines.map { it.split(",") }.flatten().map { it.toInt() }
        return (1..80).fold(input) { acc, _ ->
            acc.flatMap(::reproduce)
        }.size
    }

    override fun puzzle2(lines: List<String>): Long {
        val input = lines.map { it.split(",") }.flatten().map { it.toInt() }
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

