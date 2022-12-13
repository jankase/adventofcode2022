package day11

data class Monkey(
    val id: Int,
    val inspectionOperation: (WorryLevel) -> WorryLevel,
    val testDivisor: Int,
    val ifTrueDestinationID: Int,
    val ifFalseDestinationID: Int
)
