package day07

internal class FileSystem(initialInstructions: List<FileSystemInstruction>, val capacity: Int = 70000000) {

    val rootDirectory = Directory("/")
    private var currentPath = mutableListOf<String>()

    init {
        var currentDirectory = rootDirectory
        initialInstructions.forEach {
            when (it) {
                is FileSystemInstruction.CD -> {
                    when (it.path) {
                        "/" -> currentPath = mutableListOf()
                        ".." -> currentPath.removeLast()
                        else -> currentPath.add(it.path)
                    }
                    currentDirectory = rootDirectory.jumpToPath(currentPath)
                }
                is FileSystemInstruction.LS -> Unit
                is FileSystemInstruction.ITEM -> when (it.item) {
                    is Directory -> currentDirectory.items.add(Directory(it.item.name))
                    is File -> currentDirectory.items.add(File(it.item.name, it.item.size))
                }
            }
        }
    }

    fun findRecursive(predicate: (FileSystemItem) -> Boolean): List<FileSystemItem> = rootDirectory.findRecursive(predicate)

    fun findDirectoryToFreeUpSpace(minimalFreeSpace: Int = 30000000): FileSystemItem {
        val freeSpace = capacity - rootDirectory.size
        val spaceToFreeUp = minimalFreeSpace - freeSpace
        return findRecursive { it is Directory && it.size >= spaceToFreeUp }.minByOrNull { it.size } ?: rootDirectory
    }
}
