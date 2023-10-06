package com.vngrp.aoc2021

import com.vngrp.Day
import java.io.File
import com.vngrp.replace

class Bingo
data class BingoNumber(val value: Int, val marked: Boolean = false)
typealias BingoStrategy = (List<BingoBoard>) -> BingoBoard?
typealias BoardsAndDrawnNumbers = Pair<List<String>, List<BingoNumber>>

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

        return if (rowBingo || columnBingo) Bingo() else null
    }
}

object Day4: Day<BoardsAndDrawnNumbers>(4, 2021) {
    override fun parse(input: File): Pair<List<String>, List<BingoNumber>> = input
        .readLines()
        .let { lines ->
            lines to lines
                .first()
                .split(",")
                .map { BingoNumber(it.toInt()) }
        }


    override fun part1(input: BoardsAndDrawnNumbers) = 6//input(::quickestBoardStrategy)
    override fun part2(input: BoardsAndDrawnNumbers) = 5//input(::slowestBoardStrategy)

//    private fun playBingo(input: BingoStrategy): Int {
//        val (board, numberThatGaveBingo) = findBingoBoard(
//            setupBoards(input.first),
//            input.second,
//            ::slowestBoardStrategy
//        )
//        val sumOfUnmarked = calculateSumOfUnmarkedNumbers(board)
//
//        return sumOfUnmarked * numberThatGaveBingo.value
//    }
//
//    override fun part1(input: BingoStrategy): Number {
//        val (board, numberThatGaveBingo) = findBingoBoard(setupBoards(input.first), input.second, ::quickestBoardStrategy)
//        val sumOfUnmarked = calculateSumOfUnmarkedNumbers(board)
//
//        return sumOfUnmarked * numberThatGaveBingo.value
//    }
}

fun quickestBoardStrategy(boards: List<BingoBoard>): BingoBoard {
    return boards.first { it.check() is Bingo }
}

fun slowestBoardStrategy(boards: List<BingoBoard>): BingoBoard {
    return boards.first { it.check() !is Bingo }
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