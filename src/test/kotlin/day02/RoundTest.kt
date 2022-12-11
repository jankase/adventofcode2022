package day02

import kotlin.test.Test
import kotlin.test.assertEquals

class RoundTest {
    @Test
    fun testWithPlayersPlay() {
        assertEquals(Round(Play.ROCK, Play.ROCK, RoundResult.DRAW), Round.fromOpponentAndYourPlay("A X"))
        assertEquals(Round(Play.PAPER, Play.PAPER, RoundResult.DRAW), Round.fromOpponentAndYourPlay("B Y"))
        assertEquals(Round(Play.SCISSORS, Play.SCISSORS, RoundResult.DRAW), Round.fromOpponentAndYourPlay("C Z"))
        assertEquals(Round(Play.ROCK, Play.PAPER, RoundResult.WIN), Round.fromOpponentAndYourPlay("A Y"))
        assertEquals(Round(Play.PAPER, Play.SCISSORS, RoundResult.WIN), Round.fromOpponentAndYourPlay("B Z"))
        assertEquals(Round(Play.SCISSORS, Play.ROCK, RoundResult.WIN), Round.fromOpponentAndYourPlay("C X"))
        assertEquals(Round(Play.ROCK, Play.SCISSORS, RoundResult.LOST), Round.fromOpponentAndYourPlay("A Z"))
        assertEquals(Round(Play.PAPER, Play.ROCK, RoundResult.LOST), Round.fromOpponentAndYourPlay("B X"))
        assertEquals(Round(Play.SCISSORS, Play.PAPER, RoundResult.LOST), Round.fromOpponentAndYourPlay("C Y"))
    }

    @Test
    fun testWithOpponentPlayAndDesiredResult() {
        assertEquals(Round(Play.ROCK, Play.ROCK, RoundResult.DRAW), Round.fromOpponentPlayAndDesiredResult("A Y"))
        assertEquals(Round(Play.PAPER, Play.PAPER, RoundResult.DRAW), Round.fromOpponentPlayAndDesiredResult("B Y"))
        assertEquals(Round(Play.SCISSORS, Play.SCISSORS, RoundResult.DRAW), Round.fromOpponentPlayAndDesiredResult("C Y"))
        assertEquals(Round(Play.ROCK, Play.PAPER, RoundResult.WIN), Round.fromOpponentPlayAndDesiredResult("A Z"))
        assertEquals(Round(Play.PAPER, Play.SCISSORS, RoundResult.WIN), Round.fromOpponentPlayAndDesiredResult("B Z"))
        assertEquals(Round(Play.SCISSORS, Play.ROCK, RoundResult.WIN), Round.fromOpponentPlayAndDesiredResult("C Z"))
        assertEquals(Round(Play.ROCK, Play.SCISSORS, RoundResult.LOST), Round.fromOpponentPlayAndDesiredResult("A X"))
        assertEquals(Round(Play.PAPER, Play.ROCK, RoundResult.LOST), Round.fromOpponentPlayAndDesiredResult("B X"))
        assertEquals(Round(Play.SCISSORS, Play.PAPER, RoundResult.LOST), Round.fromOpponentPlayAndDesiredResult("C X"))
    }
}
