@file:Suppress("unused")
import kotlin.math.absoluteValue

fun gcd(a: Int, b: Int): Int = if (b == 0) a.absoluteValue else gcd(b, a % b)
fun gcd(f: Int, vararg n: Int): Int = n.fold(f, ::gcd)
fun Iterable<Int>.gcd(): Int = reduce(::gcd)

fun gcd(a: Long, b: Long): Long = if (b == 0L) a.absoluteValue else gcd(b, a % b)
fun gcd(f: Long, vararg n: Long): Long = n.fold(f, ::gcd)
fun Iterable<Long>.gcd(): Long = reduce(::gcd)

fun lcm(a: Int, b: Int): Int = (a safeTimes b) / gcd(a, b)
fun lcm(f: Int, vararg n: Int): Long = n.map { it.toLong() }.fold(f.toLong(), ::lcm)
@JvmName("lcmForInt")
fun Iterable<Int>.lcm(): Long = map { it.toLong() }.reduce(::lcm)

fun lcm(a: Long, b: Long): Long = (a safeTimes b) / gcd(a, b)
fun lcm(f: Long, vararg n: Long): Long = n.fold(f, ::lcm)
fun Iterable<Long>.lcm(): Long = reduce(::lcm)

@JvmName("productLong")
fun Iterable<Long>.product(): Long = reduce(Long::safeTimes)
@JvmName("productLong")
fun Sequence<Long>.product(): Long = reduce(Long::safeTimes)

@JvmName("productInt")
fun Iterable<Int>.product(): Long = fold(1L, Long::safeTimes)
@JvmName("productInt")
fun Sequence<Int>.product(): Long = fold(1L, Long::safeTimes)

infix fun Int.safeTimes(other: Int) = (this * other).also {
    check(other == 0 || it / other == this) { "Integer overflow at $this * $other" }
}

infix fun Long.safeTimes(other: Long) = (this * other).also {
    check(other == 0L || it / other == this) { "Long overflow at $this * $other" }
}

