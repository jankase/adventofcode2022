package day05

internal data class ContainerMoveInstruction(val quantity: Int, val source: Int, val destination: Int) {
    companion object {
        fun valueOf(value: String): ContainerMoveInstruction {
            val regexResult = """move (\d+) from (\d+) to (\d+)""".toRegex().find(value)
            if (regexResult == null) {
                error("Failed to parse input: $value")
            } else {
                val (quantity, source, destination) = regexResult.destructured
                return ContainerMoveInstruction(quantity.toInt(), source.toInt(), destination.toInt())
            }
        }
    }
}
