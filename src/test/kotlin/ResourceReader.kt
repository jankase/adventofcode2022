import java.io.File

fun readTestResourceFile(name: String) = File("src/test/resources", "$name.txt").readLines()
