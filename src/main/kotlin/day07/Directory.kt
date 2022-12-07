package day07

internal class Directory(override val name: String): FileSystemItem {
    companion object {
        fun valueOf(value: String): Directory {
            val matchResult = """/dir (\w+)/gm""".toRegex().find(value) ?: error("It is not directory")
            val (directoryName) = matchResult.destructured
            return Directory(directoryName)
        }
    }

    var items = mutableListOf<FileSystemItem>()
    override val size: Int
        get() = items.sumOf { it.size }

    fun jumpToPath(path: List<String>): Directory {
        if (path.isEmpty()) return this
        val subDirName = path.first()
        val correspondingItem = items.firstOrNull { it.name == subDirName } as? Directory ?: error("not directory exists")
        return correspondingItem.jumpToPath(path.drop(1))
    }

    fun findRecursive(predicate: (FileSystemItem) -> Boolean): List<FileSystemItem> {
        val result = mutableListOf<FileSystemItem>()
        items.forEach {
            if (predicate(it)) result.add(it)
            if (it is Directory) result += it.findRecursive(predicate)
        }
        return result
    }
}
