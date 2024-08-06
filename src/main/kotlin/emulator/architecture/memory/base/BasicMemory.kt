package emulator.architecture.memory.base

import emulator.architecture.memory.Memory

abstract class BasicMemory(
    bytes: ByteArray
): Memory(bytes) {
    abstract fun read(address: Int): Byte
    abstract fun write(address: Int, byte: Byte)
}
