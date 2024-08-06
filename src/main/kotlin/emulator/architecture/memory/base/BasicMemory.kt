package emulator.architecture.memory.base

import emulator.architecture.memory.Memory

@OptIn(ExperimentalUnsignedTypes::class)
abstract class BasicMemory(
    bytes: UByteArray
): Memory(bytes) {
    abstract fun read(address: Int): UByte
    abstract fun write(address: Int, byte: UByte)
}
