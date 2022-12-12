package day10

internal sealed interface Signal {
    var numberOfRemainingCycles: Int
    val finished: Boolean
        get() = numberOfRemainingCycles == 0
    fun endOfCycle(): Int {
        numberOfRemainingCycles--
        return if (numberOfRemainingCycles == 0)
            updateAtTheOfLife()
        else
            0
    }

    fun updateAtTheOfLife(): Int

    class NoOp : Signal {
        override var numberOfRemainingCycles: Int = 1
        override fun updateAtTheOfLife(): Int = 0
    }

    data class AddX(val registerValueChange: Int) : Signal {
        override var numberOfRemainingCycles: Int = 2

        override fun updateAtTheOfLife(): Int = registerValueChange
    }

    companion object {
        fun valueOf(value: String): Signal {
            val matchResult = ("""(\w{4}) ?(-?\d+)?""".toRegex().find(value) ?: error("Failed match")).destructured
            return when (matchResult.component1()) {
                "noop" -> NoOp()
                "addx" -> AddX(matchResult.component2().toInt())
                else -> error("Unexpected operation id: ${matchResult.component1()}")
            }
        }
    }
}
