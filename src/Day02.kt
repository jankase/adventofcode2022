fun main() {
  fun part1(input: List<String>): Int = Game(input.map { Round.fromStringPart1(it) }).totalScore()

  fun part2(input: List<String>): Int = Game(input.map { Round.fromStringPart2(it) }).totalScore()

  val testInput = readInput("Day02_test")
  check(part1(testInput) == 15)
  check(part2(testInput) == 12)

  val input = readInput("Day02")
  println(part1(input))
  println(part2(input))
}


private data class Round(val opponentPlay: Play, val yourPlay: Play, val result: RoundResult) {
  sealed class Play {
    object ROCK : Play()
    object PAPER : Play()
    object SCISSORS : Play()

    companion object {
      fun valueOf(value: String): Play {
        return when (value) {
          "A", "X" -> ROCK
          "B", "Y" -> PAPER
          "C", "Z" -> SCISSORS
          else -> throw IllegalArgumentException("No object main.Round.Play.$value")
        }
      }
    }
  }

  sealed class RoundResult {
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

  companion object {
    fun fromStringPart1(input: String): Round {
      val playInputs = inputStrings(input).map { Play.valueOf(it) }
      val opponentPlay = playInputs[0]
      val yourPlay = playInputs[1]
      return Round(opponentPlay, yourPlay, roundResult(opponentPlay, yourPlay))
    }

    fun fromStringPart2(input: String): Round {
      val playInputs = inputStrings(input)
      val opponentPlay = Play.valueOf(playInputs[0])
      val result = RoundResult.valueOf(playInputs[1])
      return Round(opponentPlay, yourPlay(opponentPlay, result), result)
    }

    private fun inputStrings(input: String): Array<String> {
      val playInputs = input.split(" ").toTypedArray()
      if (playInputs.size != 2) throw IllegalArgumentException("Expected two plays per round")
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

private data class Game(val rounds: List<Round>) {
  var score: (Round) -> Int = {
    opponentPlayScore(it.opponentPlay) + yourPlayScore(it.yourPlay) + resultScore(it.result)
  }

  var opponentPlayScore: (Round.Play) -> Int = { 0 }

  var yourPlayScore: (Round.Play) -> Int = {
    when (it) {
      Round.Play.ROCK -> 1
      Round.Play.PAPER -> 2
      Round.Play.SCISSORS -> 3
    }
  }

  var resultScore: (Round.RoundResult) -> Int = {
    when (it) {
      Round.RoundResult.LOST -> 0
      Round.RoundResult.DRAW -> 3
      Round.RoundResult.WIN -> 6
    }
  }

  fun totalScore(): Int = rounds.sumOf(score)
}
