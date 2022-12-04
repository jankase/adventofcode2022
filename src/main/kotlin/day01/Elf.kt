package day01

internal data class Elf(val calories: Int) {
    companion object {
        fun valueOf(value: List<String>): Elf = Elf(value.sumOf { it.toInt() })
    }
}
