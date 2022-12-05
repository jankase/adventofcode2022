package day05

internal class Crane9000 : Crane {
    override fun pickup(input: List<CrateID>, quantity: Int): List<CrateID> = input.takeLast(quantity).reversed()
}
