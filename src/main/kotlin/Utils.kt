import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/main/resources", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
@Suppress("unused")
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun String.extractAllIntegers(includeNegativeNumbers: Boolean = true): List<Int> =
    sequenceContainedIntegers(includeNegativeNumbers).toList()

fun String.extractAllLongs(includeNegativeNumbers: Boolean = true): List<Long> =
    sequenceContainedLongs(includeNegativeNumbers).toList()

fun Long.checkedToInt(): Int = let {
    check(it in Int.MIN_VALUE..Int.MAX_VALUE) { "Value does not fit to Int: $it" }
    it.toInt()
}

infix fun Long.safeTimes(other: Int) = (this * other).also {
    check(other == 0 || it / other == this) { "Long overflow at $this * $other" }
}

infix fun Int.safeTimes(other: Long) = (this.toLong() * other).also {
    check(other == 0L || it / other == this.toLong()) { "Long overflow at $this * $other" }
}


private val numberRegex = """(-+)?\d+""".toRegex()
private val positiveNumberRegex = """\d+""".toRegex()
fun String.sequenceContainedIntegers(includeNegativeNumbers: Boolean): Sequence<Int> =
    (if (includeNegativeNumbers) numberRegex else positiveNumberRegex).findAll(this)
        .mapNotNull { it.value.toIntOrNull() ?: warn("Number too large for Integer: ${it.value}") }

fun String.sequenceContainedLongs(includeNegativeNumbers: Boolean): Sequence<Long> =
    (if (includeNegativeNumbers) numberRegex else positiveNumberRegex).findAll(this)
        .mapNotNull { it.value.toLongOrNull() ?: warn("Number too large for Integer: ${it.value}") }

private fun <T> warn(msg: String): T? {
    println("WARNING: $msg")
    return null
}
