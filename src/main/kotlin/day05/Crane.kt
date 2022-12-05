package day05

internal interface Crane {
    fun pickup(input: List<CrateID>, quantity: Int): List<CrateID>
}
