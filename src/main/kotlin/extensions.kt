import java.io.File
import java.lang.IllegalArgumentException
import kotlin.math.ceil

fun String.read(): List<String> = File("input/$this.txt").readLines()
fun String.readTest(): List<String> = File("input/$this-test.txt").readLines()
fun <T> String.read(block: (line: String) -> T): List<T> = read().map(block)
fun <T> String.readTest(block: (line: String) -> T): List<T> = readTest().map(block)

fun <T : Any> assert(some: T, other: T) {
    if (some != other) {
        throw IllegalArgumentException("Failed to assert that $some equals $other.")
    }
}

fun List<Boolean>.toInt(): Int {
    return this
        .map {
            if (it) '1' else '0'
        }
        .joinToString("")
        .let {
            Integer.parseInt(it, 2)
        }
}

fun Int.ceilDiv(divider: Int): Int = ceil(this / divider.toDouble()).toInt()