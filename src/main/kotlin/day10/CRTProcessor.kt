package day10

internal data class CRTProcessor(
    val signals: List<Signal>,
    val screenWidth: Int = 40,
    var currentCycle: Int = 1,
    var registerX: Int = 1
) {
    private var row: Int = 0
    private val currentPixel: Int
        get() = (currentCycle - 1) - row * screenWidth
    private val currentSprite: IntRange
        get() = registerX - 1..registerX + 1
    private val processedCommands = mutableListOf<Signal>()

    fun crtOutput(): List<String> {
        val crtOutput = mutableListOf<Char>()
        val signals = this.signals.toMutableList()
        while (signals.isNotEmpty() || processedCommands.isNotEmpty()) {
            if (processedCommands.isEmpty()) {
                processedCommands.add(signals.removeFirst())
            }
            println("Current pixel $currentPixel, current sprite: $currentSprite")
            crtOutput.add(if (currentSprite.contains(currentPixel)) '#' else ' ')
            processedCommands.forEach {
                registerX += it.endOfCycle()
            }
            processedCommands.removeAll { it.finished }
            currentCycle++
            if (currentCycle - row * screenWidth > screenWidth) row++
        }
        return crtOutput.chunked(screenWidth).map { String(it.toCharArray()) }
    }
}
