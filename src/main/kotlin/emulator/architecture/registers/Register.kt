package emulator.architecture.registers

import emulator.architecture.memory.Memory

abstract class Register(
    bytes: ByteArray): Memory(bytes) {
        fun read(): ByteArray {
            return readBytes
    }
        abstract fun write(bytes:ByteArray)
}