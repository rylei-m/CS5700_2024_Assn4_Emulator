package emulator.architecture.memory

class Memory(val size: Int) {
    private val memory = ByteArray(size)

    fun read(address: Int): Byte = memory[address]
    fun write(address: Int, value: Byte) { memory[address] = value }
}