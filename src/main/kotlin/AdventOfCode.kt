import arrow.fx.coroutines.parMap
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val competitiveRange = time("05:45:00")..time("07:00:00")
    val inFocusMode = true

    if (time() in competitiveRange || inFocusMode) {
        // Quickly do current day
        // Day1.solve(0, 0)
        Day1.solve(3, 3)
    } else {
        adventOfCodeEditions
            .parMap { it.saveChristmas() }
    }
}

interface AdventOfCode {
    val year: Int

    fun saveChristmas() = printYear().also { solvePuzzles() }
    fun solvePuzzles()
}
