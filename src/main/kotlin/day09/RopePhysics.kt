package day09

internal class RopePhysics(private val moveDirections: List<MoveDirection>) {
    companion object {
        fun valueOf(value: List<String>): RopePhysics {
            return RopePhysics(value.map { MoveDirection.valueOf(it) })
        }
    }

    fun performMoveInstructions(): Set<Position> {
        val rope = Rope(Position(0, 0), Position(0, 0))
        val result = moveDirections.fold(setOf(Position(0, 0))) { acc, moveDirection ->
            val tailMoves = rope.move(moveDirection)
            println(moveDirection)
            println(tailMoves)
            acc + tailMoves
        }
        return result
    }
}
