package day05

typealias CrateID = Char

internal data class Ship(val load: MutableMap<Int, List<CrateID>>) {
    companion object {
        fun parseLoad(input: List<String>): Ship {
            val maxLength = input.maxOf { it.length }
            val remainder = maxLength % 4
            val effectiveLengthForPadding = maxLength + if (remainder == 0) 0 else {
                4 - remainder
            }
            val normalizedInput = input.map { it.padEnd(effectiveLengthForPadding) }.reversed()
            val chunked = normalizedInput
                .map { it.chunked(4).map { string -> string.trim() }.toTypedArray() }
                .toMutableList()
            val ids = chunked.removeAt(0).map { it.toInt() }
            val loadMap: MutableMap<Int, List<CrateID>> = mutableMapOf()
            ids.forEachIndexed { index, id ->
                val crateIDs = chunked.mapNotNull {
                    val string = it[index]
                    return@mapNotNull if (string.isBlank()) null else string[1]
                }
                loadMap[id] = crateIDs
            }
            return Ship(loadMap)
        }
    }

    fun topContainers(): String {
        return String(load.keys.sorted().mapNotNull { load[it]?.lastOrNull() }.toCharArray())
    }
}
