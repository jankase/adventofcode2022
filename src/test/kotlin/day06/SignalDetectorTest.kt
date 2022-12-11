package day06

import kotlin.test.Test
import kotlin.test.assertEquals

class SignalDetectorTest {
    @Test
    fun testPacketStartMarker() {
        assertEquals(7, SignalDetector("mjqjpqmgbljsphdztnvjfqwrcgsmlb").findPacketStartMarker())
        assertEquals(5, SignalDetector("bvwbjplbgvbhsrlpgdmjqwftvncz").findPacketStartMarker())
        assertEquals(6, SignalDetector("nppdvjthqldpwncqszvftbrmjlhg").findPacketStartMarker())
        assertEquals(10, SignalDetector("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").findPacketStartMarker())
        assertEquals(11, SignalDetector("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw").findPacketStartMarker())
    }
    @Test
    fun testMessageStartMarker() {
        assertEquals(19, SignalDetector("mjqjpqmgbljsphdztnvjfqwrcgsmlb").findMessageStartMarker())
        assertEquals(23, SignalDetector("bvwbjplbgvbhsrlpgdmjqwftvncz").findMessageStartMarker())
        assertEquals(23, SignalDetector("nppdvjthqldpwncqszvftbrmjlhg").findMessageStartMarker())
        assertEquals(29, SignalDetector("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").findMessageStartMarker())
        assertEquals(26, SignalDetector("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw").findMessageStartMarker())
    }
}
