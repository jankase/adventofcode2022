import java.io.File
import java.net.URL

// inspired by Olaf Gottschalk
object AOC {
    private val web = AoCWebInterface(getSessionCookie())

    fun getPuzzleInput(day: Int, year: Event): List<String> {
        val cached = readInputFileOrNull(day, year)
        if (!cached.isNullOrEmpty()) return cached
        return web.downloadInput(FullyQualifiedDate(day, year)).onSuccess {
            writeInputFile(day, year, it)
        }.getOrElse {
            listOf("Unable to download $day/$year: $it")
        }
    }

    private fun readInputFileOrNull(day: Int, year: Event): List<String>? {
        val file = File(fileNameFor(day, year))
        file.exists() || return null
        return file.readLines()
    }

    private fun writeInputFile(day: Int, year: Event, puzzle: List<String>) {
        File(pathNameForYear(year)).mkdirs()
        File(fileNameFor(day, year)).writeText(puzzle.joinToString("\n"))
    }

    private fun fileNameFor(day: Int, year: Event) = "${pathNameForYear(year)}/Day${"%02d".format(day)}.txt"

    private fun pathNameForYear(year: Event) = "puzzles/$year"

    private fun getSessionCookie() =
        System.getenv("AOC_COOKIE") ?: warn("No cookies available")
}

class AoCWebInterface(private val sessionCookie: String?) {
    companion object {
        private const val BASE_URL = "https://adventofcode.com"

        fun FullyQualifiedDate.toURI() = "$BASE_URL/$year/day/$day"
    }

    fun downloadInput(fullyQualifiedDate: FullyQualifiedDate): Result<List<String>> = runCatching {
        with(fullyQualifiedDate) {
            println("Downloading puzzle for $fullyQualifiedDate")
            val cookies = mapOf("session" to sessionCookie)
            val url = URL("${fullyQualifiedDate.toURI()}/input")

            with(url.openConnection()) {
                setRequestProperty(
                    "Cookie", cookies.entries.joinToString(separator = "; ") { (k, v) -> "$k=$v" }
                )
                connect()
                getInputStream().bufferedReader().readLines()
            }
        }
    }
}
