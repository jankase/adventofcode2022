package day06

data class SignalDetector(val signal: String) {
    fun findPacketStartMarker() = findMarker(4)
    fun findMessageStartMarker() = findMarker(14)

    private fun findMarker(windowSize: Int): Int {
        val signalWindows = signal.windowed(windowSize)
        val firstUniqueWindow = signalWindows.indexOfFirst { it.chars().count() == it.chars().distinct().count() }
        return firstUniqueWindow + windowSize
    }
}
