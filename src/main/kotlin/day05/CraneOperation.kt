package day05

import split

internal data class CraneOperation(val ship: Ship, val instructions: List<ContainerMoveInstruction>, val crane: Crane) {
    companion object {
        fun valueOf(value: List<String>, crane: Crane): CraneOperation {
            val parts = value.split({ it.isNotBlank() }, true)
            return CraneOperation(
                Ship.parseLoad(parts.first()),
                parts.last().map { ContainerMoveInstruction.valueOf(it) },
                crane
            )
        }
    }

    fun performInstructions() {
        instructions.forEach {
            val sourceList = ship.load[it.source]
            val destinationList = ship.load[it.destination]
            if (sourceList == null || destinationList == null || sourceList.size < it.quantity)
                error("Operation could not be performed")
            val newSourceList = sourceList.dropLast(it.quantity)
            val newDestination = destinationList + crane.pickup(sourceList, it.quantity)
            ship.load[it.source] = newSourceList
            ship.load[it.destination] = newDestination
        }
    }
}
