package day09

internal data class Position(var x: Int, var y: Int) {
    fun closeTo(input: Position): Boolean =
        (x - 1..x + 1).contains(input.x) && (y - 1..y + 1).contains(input.y)
}
