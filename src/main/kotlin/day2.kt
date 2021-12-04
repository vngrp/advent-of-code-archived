enum class Direction { UP, DOWN, FORWARD }
data class Command(val direction: String, val amount: Int)
data class AimedPosition(val depth: Int, val horizontal: Int, val aim: Int)
data class Position(val depth: Int, val horizontal: Int)

fun main() {
    fun puzzle1(commands: List<Command>): Int {
        return commands
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
    fun puzzle2(commands: List<Command>): Int {
        return commands
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

    val test = "day2".readTest {
        val (instruction, amount) = it.split(' ')
        Command(instruction, amount.toInt())
    }

    check(puzzle1(test) == 150)
    check(puzzle2(test) == 900)

    val commands = "day2".read {
        val (instruction, amount) = it.split(' ')
        Command(instruction, amount.toInt())
    }

    println(puzzle1(commands))
    println(puzzle2(commands))
}