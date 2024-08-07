package emulator.architecture.registers

import emulator.architecture.memory.Memory

@OptIn(ExperimentalUnsignedTypes::class)
abstract class Register(
    bytes: UByteArray
): Memory(bytes) {
        fun read(): UByteArray {
            val readUBytes = bytes.copyOf()
            println(readUBytes)
            return readUBytes
    }
        abstract fun write(bytes: UByteArray)
}
