package emulator.architecture.memory

abstract class Memory @OptIn(ExperimentalUnsignedTypes::class) constructor(
    val bytes: UByteArray
)
