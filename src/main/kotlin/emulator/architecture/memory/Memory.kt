package emulator.architecture.memory

abstract class Memory {
    open fun read(address: Int): Byte? {return null }
    open fun write(address: Int, value: Byte) {}
}
