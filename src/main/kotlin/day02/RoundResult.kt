package day02

internal sealed class RoundResult {
    object LOST : RoundResult()
    object DRAW : RoundResult()
    object WIN : RoundResult()

    companion object {
        fun valueOf(value: String): RoundResult {
            return when (value) {
                "X" -> LOST
                "Y" -> DRAW
                "Z" -> WIN
                else -> throw IllegalArgumentException("No object main.Round.RoundResult.$value")
            }
        }
    }
}
