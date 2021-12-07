enum class Direction { UP, DOWN, FORWARD }
data class Command(val direction: String, val amount: Int)
data class AimedPosition(val depth: Int, val horizontal: Int, val aim: Int)
data class Position(val depth: Int, val horizontal: Int)

class Day2: Day {
    override val examplePuzzle1 = 150
    override val examplePuzzle2 = 900

    override fun puzzle1(lines: List<String>): Int {
        return lines
                .map(::toCommand)
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
    override fun puzzle2(lines: List<String>): Int {
        return lines
                .map(::toCommand)
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
    }

    private fun toCommand(line: String): Command {
        val (instruction, amount) = line.split(' ')

        return Command(instruction, amount.toInt())
    }
}