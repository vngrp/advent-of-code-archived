package aoc2018

import Day
import parseInts

object Day1 : Day<List<Int>>(1, 2018) {
    override fun parse() = ::parseInts
    override fun part1(input: List<Int>) = input.sum()
    override fun part2(input: List<Int>) = findDuplicate(input)

    private fun findDuplicate(input: List<Int>): Int {
        val seen = mutableSetOf<Int>()
        var sum = 0
        while (true) {
            for (number in input) {
                sum += number
                if (sum in seen) return sum
                seen.add(sum)
            }
        }
    }
}