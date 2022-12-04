package day02

internal sealed class Play {
    object ROCK : Play()
    object PAPER : Play()
    object SCISSORS : Play()

    companion object {
        fun valueOf(value: String): Play {
            return when (value) {
                "A", "X" -> ROCK
                "B", "Y" -> PAPER
                "C", "Z" -> SCISSORS
                else -> error("No object main.Round.Play.$value")
            }
        }
    }
}
