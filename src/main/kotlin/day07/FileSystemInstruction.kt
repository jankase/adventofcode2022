package day07

internal sealed class FileSystemInstruction {
    data class CD(val path: String) : FileSystemInstruction() {
        companion object {
            fun valueOf(value: String): CD {
                return CD(value.substring(startIndex = 5).trim())
            }
        }
    }

    object LS : FileSystemInstruction()
    data class ITEM(val item: FileSystemItem) : FileSystemInstruction() {
        companion object {
            fun valueOf(value: String): ITEM {
                val dirMatch = """dir (\w+)""".toRegex().find(value)
                return if (dirMatch == null) {
                    val fileMatch = """(\d+) (.+)""".toRegex().find(value) ?: error("Not file system item")
                    val (size, name) = fileMatch.destructured
                    ITEM(File(name, size.toInt()))
                } else {
                    val (name) = dirMatch.destructured
                    ITEM(Directory(name))
                }
            }
        }
    }

    companion object {
        fun valueOf(value: List<String>): List<FileSystemInstruction> {
            return value.map {
                when {
                    it.startsWith("$ cd") -> CD.valueOf(it)
                    it.startsWith("$ ls") -> LS
                    else -> ITEM.valueOf(it)
                }
            }
        }
    }
}

private operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)
