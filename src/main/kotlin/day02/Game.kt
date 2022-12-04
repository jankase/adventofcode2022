package day02

internal data class Game(val rounds: List<Round>) {
    var score: (Round) -> Int = {
        opponentPlayScore(it.opponentPlay) + yourPlayScore(it.yourPlay) + resultScore(it.result)
    }

    var opponentPlayScore: (Play) -> Int = { 0 }

    var yourPlayScore: (Play) -> Int = {
        when (it) {
            Play.ROCK -> 1
            Play.PAPER -> 2
            Play.SCISSORS -> 3
        }
    }

    var resultScore: (RoundResult) -> Int = {
        when (it) {
            RoundResult.LOST -> 0
            RoundResult.DRAW -> 3
            RoundResult.WIN -> 6
        }
    }

    fun totalScore(): Int = rounds.sumOf(score)
}
