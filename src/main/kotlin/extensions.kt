import java.io.File
import java.lang.IllegalArgumentException

fun String.read(): List<String> = File("input/$this.txt").readLines()
fun String.readTextTest(): String = File("input/$this-test.txt").readText()
fun String.readTest(): List<String> = File("input/$this-test.txt").readLines()
fun <T> String.read(block: (line: String) -> T): List<T> = read().map(block)
fun String.readText(): String = File("input/$this.txt").readText()
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

val <T> List<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()

fun <T> List<T>.replace(old: T, new: T): List<T> {
    val index = indexOf(old)
    if (index < 0) return this

    val list = toMutableList()
    list[index] = new

    return list
}
