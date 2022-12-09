package day09

internal data class Rope(val head: Position, val tail: Position) {
    fun move(direction: MoveDirection): Set<Position> {
        val result = mutableSetOf<Position>()
        moveHead(direction)
        result += moveTail()
        return result
    }

    private fun moveHead(direction: MoveDirection) {
        when (direction) {
            is MoveDirection.RIGHT -> head.x += direction.distance
            is MoveDirection.LEFT -> head.x -= direction.distance
            is MoveDirection.UP -> head.y += direction.distance
            is MoveDirection.DOWN -> head.y -= direction.distance
        }
    }

    private fun moveTail(): Set<Position> {
        val result = mutableSetOf<Position>()
        while (!head.closeTo(tail)) {
            val originalTail = tail.copy()
            when {
                head.y == tail.y -> if (head.x > tail.x) tail.x++ else tail.x--
                head.x == tail.x -> if (head.y > tail.y) tail.y++ else tail.y--
                else -> {
                    tail.x += if (head.x > tail.x) 1 else -1
                    tail.y += if (head.y > tail.y) 1 else -1
                }
            }
            println("Move from $originalTail to $tail")
            result.add(tail.copy())
        }
        return result
    }
}
