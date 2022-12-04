package day02

internal data class Round(val opponentPlay: Play, val yourPlay: Play, val result: RoundResult) {
    companion object {
        fun fromOpponentAndYourPlay(input: String): Round {
            val playInputs = inputStrings(input).map { Play.valueOf(it) }
            val opponentPlay = playInputs[0]
            val yourPlay = playInputs[1]
            return Round(opponentPlay, yourPlay, roundResult(opponentPlay, yourPlay))
        }

        fun fromOpponentPlayAndDesiredResult(input: String): Round {
            val playInputs = inputStrings(input)
            val opponentPlay = Play.valueOf(playInputs[0])
            val result = RoundResult.valueOf(playInputs[1])
            return Round(opponentPlay, yourPlay(opponentPlay, result), result)
        }

        private fun inputStrings(input: String): Array<String> {
            val playInputs = input.split(" ").toTypedArray()
            if (playInputs.size != 2) error("Expected two plays per round")
            return playInputs
        }

        private fun roundResult(opponentPlay: Play, yourPlay: Play): RoundResult {
            return when {
                opponentPlay == yourPlay -> RoundResult.DRAW
                opponentPlay == Play.ROCK && yourPlay == Play.PAPER ||
                    opponentPlay == Play.PAPER && yourPlay == Play.SCISSORS ||
                    opponentPlay == Play.SCISSORS && yourPlay == Play.ROCK -> RoundResult.WIN

                else -> RoundResult.LOST
            }
        }

        private fun yourPlay(opponentPlay: Play, desiredResult: RoundResult): Play {
            return when (desiredResult) {
                RoundResult.DRAW -> opponentPlay
                RoundResult.WIN -> when (opponentPlay) {
                    Play.ROCK -> Play.PAPER
                    Play.PAPER -> Play.SCISSORS
                    Play.SCISSORS -> Play.ROCK
                }

                RoundResult.LOST -> when (opponentPlay) {
                    Play.ROCK -> Play.SCISSORS
                    Play.PAPER -> Play.ROCK
                    Play.SCISSORS -> Play.PAPER
                }
            }
        }
    }
}
