package day07

import readTestResourceFile
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FileSystemTests {

    private lateinit var sut: FileSystem

    @BeforeTest
    fun setup() {
        sut = FileSystem(FileSystemInstruction.valueOf(readTestResourceFile("Day07")))
    }

    @Test
    fun testInitFileSystem() {
        assertEquals(4, sut.rootDirectory.items.size)
    }

    @Test
    fun testFindRecursive() {
        val directoriesUpTo100000 = sut.findRecursive { it is Directory && it.size <= 100000 }
        assertEquals(95437, directoriesUpTo100000.sumOf { it.size })
    }

    @Test
    fun testFindDirectoryToDelete() {
        assertEquals(24933642, sut.findDirectoryToFreeUpSpace().size)
    }
}
