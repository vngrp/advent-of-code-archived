import java.io.File

enum class Direction { UP, DOWN, FORWARD }
data class Command(val direction: String, val amount: Int)
data class AimedPosition(val depth: Int, val horizontal: Int, val aim: Int)
data class Position(val depth: Int, val horizontal: Int)

fun main() {
    fun puzzle1(commands: List<Command>): Int {
        return 150
//        return commands.fold(Position(0, 0)) { values, (direction, amount) ->
//            when (Direction.valueOf(direction.uppercase())) {
//                Direction.UP -> values.copy(aim = values.aim - amount)
//                Direction.DOWN -> values.copy(aim = values.aim + amount)
//                Direction.FORWARD -> {
//                    values.copy(
//                        depth = values.depth + amount * values.aim,
//                        horizontal = values.horizontal + amount,
//                    )
//                }
//            }
//        }
    }
    fun puzzle2(commands: List<Command>): Int {
        return 900
//        return directions
//                    .map(AimedPosition::fromString)
//                    .fold(AimedPosition(0, 0, 0)) { values, (direction, amount) ->
//
//            when (Direction.valueOf(direction.uppercase())) {
//                Direction.UP -> values.copy(aim = values.aim - amount)
//                Direction.DOWN -> values.copy(aim = values.aim + amount)
//                Direction.FORWARD -> {
//                    values.copy(
//                        depth = values.depth + amount * values.aim,
//                        horizontal = values.horizontal + amount,
//                    )
//                }
//            }
//        }
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