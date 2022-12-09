package day09

internal class RopePhysics(private val moveDirections: List<MoveDirection>) {
    companion object {
        fun valueOf(value: List<String>): RopePhysics {
            return RopePhysics(value.map { MoveDirection.valueOf(it) })
        }
    }

    fun performMoveInstructions(numberOfKnots: Int = 2): Set<Position> {
        val rope = Rope(numberOfKnots)
        val result = moveDirections.fold(setOf(Position(0, 0))) { acc, moveDirection ->
            val tailMoves = rope.move(moveDirection)
            val result = acc + tailMoves
            if (numberOfKnots > 2) {
                println("Move direction: $moveDirection")
                println("Added ${result - acc}")
            }
            result
        }
        return result
    }
}
