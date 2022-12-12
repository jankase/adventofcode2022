package day10

internal data class SignalProcessor(val signals: List<Signal>, var registerX: Int = 1, var currentCycle: Int = 1) {
    private var nextSignalCheckCycleNumber = 20
    private val signalCheckInterval = 40
    private val processedCommands = mutableListOf<Signal>()

    fun run(numberOfChecks: Int = 6): Int {
        var result = 0
        var remainingNumberOfChecks = numberOfChecks
        val signals = this.signals.toMutableList()
        while (remainingNumberOfChecks > 0 && signals.isNotEmpty()) {
            if (processedCommands.isEmpty()) {
                processedCommands.add(signals.removeFirst())
            }
            if (currentCycle == nextSignalCheckCycleNumber) {
                result += registerX * currentCycle
                nextSignalCheckCycleNumber += signalCheckInterval
                remainingNumberOfChecks--
            }
            processedCommands.forEach {
                registerX += it.endOfCycle()
            }
            processedCommands.removeAll { it.finished }
            currentCycle++
        }
        return result
    }
}
