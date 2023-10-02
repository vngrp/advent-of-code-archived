package aoc2021

import Day
import sorted

class Day8: Day {
    override val examplePuzzle1 = 26
    override val examplePuzzle2 = 61229

    override fun puzzle1(lines: List<String>): Int {
        return lines
                .flatMap { it.substringAfter("| ").split(" ") }.map { it.length }
                .filter { listOf(2, 3, 4, 7).contains(it) }
                .size
    }

    override fun puzzle2(lines: List<String>): Int {
        return lines
            .map {
                Pair(
                    deduceConfiguration(it.substringBefore(" |").split(" ")),
                    it.substringAfter("| ").split(" ")
                )
            }.sumOf { (digits, after) ->
                after
                    .map { digits.indexOfFirst { item -> it.length == item.length && it.sorted() == item.sorted() } }
                    .joinToString("")
                    .toInt()
            }
    }

    private fun deduceConfiguration(digits: List<String>): List<String> {
        val one = digits.first { it.length == 2 }
        val three = digits.first { it.length == 5 && it.contains(one[0]) && it.contains(one[1]) }
        val four = digits.first { it.length == 4 }
        val seven = digits.single { it.length == 3 }
        val eight = digits.single { it.length == 7 }
        val missing = ('a'..'g').single { (one + three + four).toCharArray().contains(it).not() }
        val five = digits.single { it.length == 5 && it != three && it.contains(missing).not() }
        val nine = digits.single { it.length == 6 && it.contains(missing).not() }
        val two = digits.single { it.length == 5 && it.contains(missing) }
        val zero = digits.single { it.length == 6 && it.contains(missing) && it.contains(one[0]) && it.contains(one[1]) }
        val six = digits.single { it.length == 6 && it.contains(missing) && it != zero }

        return listOf(zero, one, two, three, four, five, six, seven, eight, nine)
    }
}

