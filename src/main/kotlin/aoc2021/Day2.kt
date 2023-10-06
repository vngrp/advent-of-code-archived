package aoc2021

import Day
import java.io.File

data class Command(val direction: String, val amount: Int)

private enum class Direction { UP, DOWN, FORWARD }
private data class AimedPosition(val depth: Int, val horizontal: Int, val aim: Int)
private data class Position(val depth: Int, val horizontal: Int)

object Day2: Day<List<Command>>(2, 2021) {

    override fun parse(input: File) = input
        .readLines()
        .map { it.split(' ') }
        .map { (instruction, amount) ->
            Command(instruction, amount.toInt())
        }

    override fun part2(input: List<Command>) = input
        .fold(AimedPosition(0, 0, 0)) { values, (direction, amount) ->
            when (Direction.valueOf(direction.uppercase())) {
                Direction.UP -> values.copy(aim = values.aim - amount)
                Direction.DOWN -> values.copy(aim = values.aim + amount)
                Direction.FORWARD -> {
                    values.copy(
                        depth = values.depth + amount * values.aim,
                        horizontal = values.horizontal + amount,
                    )
                }
            }
        }.let {
            it.depth * it.horizontal
        }

    override fun part1(input: List<Command>) = input
        .fold(Position(0, 0)) { position, (instruction, amount) ->
            when (Direction.valueOf(instruction.uppercase())) {
                Direction.UP -> position.copy(depth = position.depth - amount)
                Direction.DOWN -> position.copy(depth = position.depth + amount)
                Direction.FORWARD -> position.copy(horizontal = position.horizontal + amount)
            }
        }
        .let {
            it.depth * it.horizontal
        }
}