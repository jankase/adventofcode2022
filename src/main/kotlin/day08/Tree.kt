package day08

data class Tree(
    val row: Int,
    val column: Int,
    val height: Int,
    var isVisible: Boolean = false,
    var scenicScore: Int = 0
)
