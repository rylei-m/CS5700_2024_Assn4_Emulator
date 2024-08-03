package emulator.architecture.memory.RamOrRom

import emulator.architecture.memory.Memory

class Ram: Memory {
    private val memory = ByteArray(4096)

    override fun read(address: Int): Byte = memory[address]

    override fun write(address: Int, value: Byte) {
        memory[address] = value
    }
}
