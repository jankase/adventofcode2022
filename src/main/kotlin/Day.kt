// inspired by Olaf Gottschalk

import com.github.ajalt.mordant.terminal.Terminal

val aocTerminal = Terminal()
var verbose = true

@JvmInline
value class Event(private val year: Int) {
    init {
        require(year >= 2015) { "Invalid year $year" }
    }

    override fun toString(): String = "$year"
}

data class FullyQualifiedDate(val day: Int, val year: Event) {
    init {
        require(day in 1..25) { "Invalid day $day" }
    }

    override fun toString(): String = "$year day $day"
}

@Suppress("MemberVisibilityCanBePrivate")
open class Day private constructor(
    fqd: FullyQualifiedDate,
    val title: String
) {
    constructor(day: Int, year: Int, title: String = "Unknown") : this(
        FullyQualifiedDate(day, Event(year)), title
    )

    val day = fqd.day
    val year = fqd.year

    private val header: Unit by lazy { if (verbose) println("--- AoC $year, Day $day $title ---\n") }
    private val rawInput: List<String> by lazy { globalTestData?.split("\n") ?: AOC.getPuzzleInput(day, year) }

    val input: List<String> by lazy { rawInput.show("Raw") }
    var groupDelimiter: (String) -> Boolean = String::isEmpty
    var onlyDataLines: (String) -> Boolean = String::isNotBlank

    val inputAsGroups: List<List<String>> by lazy { groupedInput(groupDelimiter) }
    val part1: Any? by lazy { part1() }
    val part2: Any? by lazy { part2() }

    fun groupedInput(delimiter: (String) -> Boolean): List<List<String>> {
        val result = mutableListOf<List<String>>()
        var currentSubList: MutableList<String>? = null
        for (line in rawInput) {
            if (delimiter(line)) {
                currentSubList = null
            } else {
                if (currentSubList == null) {
                    currentSubList = mutableListOf(line)
                    result += currentSubList
                } else {
                    currentSubList += line
                }
            }
        }
        return result.show("Chunked into ${result.size} chunks of")
    }

    open fun part1(): Any? = NotYetImplemented
    open fun part2(): Any? = NotYetImplemented

    fun solve() {
        header
        runWithTiming("1") { part1 }
        runWithTiming("2") { part2 }
    }

    private fun <T : Any?> List<T>.show(type: String, maxLines: Int = 10): List<T> {
        if (!verbose) return this
        header

        with(listOfNotNull(type.takeIf { it.isNotEmpty() }, "input data").joinToString { " " }) {
            println("==== $this ${"=".repeat(50 - length - 6)}")
        }
        val idxWidth = lastIndex.toString().length
        preview(maxLines) { idx, data ->
            val original = rawInput.getOrNull(idx)
            val s = when {
                rawInput.size != this.size -> "$data"
                original != "$data" -> "${original.restrictWidth(40, 40)} => $data"
                else -> original
            }
            println("${idx.toString().padStart(idxWidth)}: ${s.restrictWidth(0, 160)}")
        }
        println("=".repeat(50))
        return this
    }

    private fun <T> List<T>.preview(maxLines: Int, f: (idx: Int, data: T) -> Unit) {
        if (size <= maxLines) {
            forEachIndexed(f)
        } else {
            val cut = (maxLines - 1) / 2
            (0 until maxLines - cut - 1).forEach { f(it, this[it]!!) }
            if (size > maxLines) println("....")
            (lastIndex - cut + 1..lastIndex).forEach { f(it, this[it]!!) }
        }
    }

    private fun Any?.restrictWidth(minWidth: Int, maxWidth: Int) = with("$this") {
        when {
            length > maxWidth -> substring(0, maxWidth - 3) + "..."
            length < minWidth -> padEnd(minWidth)
            else -> this
        }
    }

    object NotYetImplemented {
        override fun toString(): String = "not yet implemented"
    }
}
