class Day9: Day {
    override val examplePuzzle1 = 0
    override val examplePuzzle2 = 0

    override fun puzzle1(lines: List<String>): Int {
        return lines
            .foldIndexed(0) { rowI, acc, line ->
                (acc + line.filterIndexed { i, num ->
                    val adjacents = mutableListOf<Char>()

                    if (i > 0 && i < line.length - 1) {
                        adjacents.add(lines[rowI][i - 1])
                        adjacents.add(lines[rowI][i + 1])
                    } else if (i > 0) {
                        adjacents.add(lines[rowI][i - 1])
                    } else if (i < line.length - 1) {
                        adjacents.add(lines[rowI][i + 1])
                    }

                    if (rowI > 0 && rowI < lines.size - 1) {
                        adjacents.add(lines[rowI - 1][i])
                        adjacents.add(lines[rowI + 1][i])
                    } else if (rowI > 0) {
                        adjacents.add(lines[rowI - 1][i])
                    } else if (rowI < lines.size - 1) {
                        adjacents.add(lines[rowI + 1][i])
                    }

                    if (adjacents.all { it > num }) {
                        println(adjacents)
                        println(num)
                    }

                    adjacents.all { it > num }
                }.let { if (it.isEmpty()) 0 else {
                    println(it)
                    1 + it.toInt()
                } })
            }

    }

    override fun puzzle2(lines: List<String>): Int {
        return lines
                .count()
    }
}

