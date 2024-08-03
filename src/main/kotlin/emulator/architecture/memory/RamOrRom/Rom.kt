package emulator.architecture.memory.RamOrRom

import emulator.architecture.memory.Memory

class Rom: Memory() {
    private val memory = ByteArray(4096)  // 4KB ROM

    override fun read(address: Int): Byte = memory[address]

    override fun write(address: Int, value: Byte) {
        throw UnsupportedOperationException("ROM is read-only")
    }

    fun load(data: ByteArray) {
        data.copyInto(memory)
    }
}