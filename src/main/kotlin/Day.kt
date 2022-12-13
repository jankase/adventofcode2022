import java.io.File

open class Day(private val year: Int, private val day: Int, private val test: Boolean = false) {
    private val rawInput: List<String> by lazy {
        val homeDir = "${if (test) "src/test" else "src/main"}/resources/"
        File(homeDir, "Day$year${day.toString().padStart(2, '0')}.txt").readLines()
    }

    var groupDelimiter: (String) -> Boolean = String::isBlank
    val inputAsGroups: List<List<String>> by lazy { groupedInput(groupDelimiter) }

    fun groupedInput(delimiter: (String) -> Boolean): List<List<String>> {
        val result = mutableListOf<List<String>>()
        var currentSublist: MutableList<String>? = null
        for (line in rawInput) {
            if (delimiter(line)) {
                currentSublist = null
            } else {
                if (currentSublist == null) {
                    currentSublist = mutableListOf(line)
                    result += currentSublist
                } else {
                    currentSublist.add(line)
                }
            }
        }
        return result
    }
}
