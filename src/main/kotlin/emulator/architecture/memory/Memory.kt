package emulator.architecture.memory

abstract class Memory {
    fun read(address: Int): Byte
    fun write(address: Int, value: Byte)
}