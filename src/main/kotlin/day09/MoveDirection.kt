package day09

internal sealed interface MoveDirection {
    val distance: Int

    data class LEFT(override val distance: Int) : MoveDirection
    data class RIGHT(override val distance: Int) : MoveDirection
    data class UP(override val distance: Int) : MoveDirection
    data class DOWN(override val distance: Int) : MoveDirection

    companion object {
        fun valueOf(value: String): MoveDirection {
            val (directionString, directionValue) =
                ("""(\w) (\d+)""".toRegex().find(value) ?: error("Incorrect input")).destructured
            return when (directionString) {
                "L" -> LEFT(directionValue.toInt())
                "R" -> RIGHT(directionValue.toInt())
                "U" -> UP(directionValue.toInt())
                "D" -> DOWN(directionValue.toInt())
                else -> error("Invalid direction")
            }
        }
    }
}
