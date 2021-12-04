
class Bingo
data class BingoNumber(val value: Int, val marked: Boolean = false)

data class BingoBoard(val rows: List<List<BingoNumber>>, val columns: List<List<BingoNumber>>) {
    fun mark(number: BingoNumber): BingoBoard {
        val rows = rows.map {
            it.replace(
                old = number,
                new = number.copy(marked = true)
            )
        }
        val columns = columns.map {
            it.replace(
                old = number,
                new = number.copy(marked = true)
            )
        }
        return BingoBoard(rows, columns)
    }
    fun check(): Bingo? {
        val rowBingo = rows.any { row -> row.all { it.marked } }
        val columnBingo = columns.any { column -> column.all { it.marked } }

        return if (rowBingo || columnBingo) Bingo()!! else null
    }
}

fun main() {
    fun puzzle1(boards: List<BingoBoard>, drawnNumbers: List<BingoNumber>): Int {
        val (board, numberThatGaveBingo) = findBingoBoard(boards, drawnNumbers, ::quickestBoardStrategy)
        val sumOfUnmarked = calculateSumOfUnmarkedNumbers(board)

        return sumOfUnmarked * numberThatGaveBingo.value
    }
    fun puzzle2(boards: List<BingoBoard>, drawnNumbers: List<BingoNumber>): Int {
        val (board, numberThatGaveBingo) = findBingoBoard(boards, drawnNumbers, ::slowestBoardStrategy)
        val sumOfUnmarked = calculateSumOfUnmarkedNumbers(board)

        return sumOfUnmarked * numberThatGaveBingo.value
    }

    val testLines = "day4".readTest()
    val testDrawn = testLines.first().split(",").map { BingoNumber(it.toInt()) }
    val testBoards = setupBoards(testLines)

    assert(puzzle1(testBoards, testDrawn), 4512)
    assert(puzzle2(testBoards, testDrawn), 1924)

    val lines = "day4".read()
    val drawn = lines.first().split(",").map { BingoNumber(it.toInt()) }
    val boards = setupBoards(lines)

    println(puzzle1(boards, drawn))
    println(puzzle2(boards, drawn))
}

fun quickestBoardStrategy(boards: List<BingoBoard>): BingoBoard? {
    return boards.find { it.check() is Bingo }
}

fun slowestBoardStrategy(boards: List<BingoBoard>): BingoBoard? {
    return boards.singleOrNull { it.check() !is Bingo }
}

fun calculateSumOfUnmarkedNumbers(board: BingoBoard): Int {
    return board.rows.fold(0) { acc, row ->
        acc + row.filter { !it.marked }
            .sumOf { it.value }
    }
}

private fun findBingoBoard(
    boards: List<BingoBoard>,
    drawnNumbers: List<BingoNumber>,
    strategy: (boards: List<BingoBoard>) -> BingoBoard?,
    index: Int = 0
): Pair<BingoBoard, BingoNumber> {
    val bingoBoard = strategy(boards)

    return if (bingoBoard != null && bingoBoard.check() is Bingo) {
        bingoBoard to drawnNumbers[index - 1]
    } else if (bingoBoard != null) {
        findBingoBoard(
            boards = listOf(bingoBoard),
            drawnNumbers = drawnNumbers,
            strategy = ::quickestBoardStrategy
        )
    } else {
        val marked = boards.map { it.mark(drawnNumbers[index]) }
        findBingoBoard(marked, drawnNumbers, strategy, index + 1)
    }
}

private fun setupBoards(lines: List<String>): List<BingoBoard> {
    val regex = Regex("\\d{1,2}+")
    val numbers = lines
        .drop(1)
        .toString()
        .let {
            regex
                .findAll(it)
                .toList()
                .map { number ->
                    number.value.toInt().let { num -> BingoNumber(num) }
                }
        }

    val boards = numbers.chunked(25).map { board ->
        val rows = board.chunked(5)
        val columns = (0..4).map { i ->
            board.filterIndexed { index, _ ->
                index % 5 == i
            }
        }

        BingoBoard(rows, columns.toList())
    }

    return boards
}