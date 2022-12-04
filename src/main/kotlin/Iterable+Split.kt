import kotlin.math.min

fun <T> Iterable<T>.split(predicate: (T) -> Boolean, removeSeparator: Boolean): List<List<T>> {
    var remaining = this.toList()
    val result = mutableListOf<List<T>>()
    while (remaining.isNotEmpty()) {
        val newGroup = remaining.takeWhile(predicate)
        result.add(newGroup)
        val fromIndex = newGroup.size + if (removeSeparator) 1 else 0
        remaining = remaining.subList(min(fromIndex, remaining.size), remaining.size)
    }
    return result
}
