package emulator.architecture.memory.base.types

import emulator.architecture.memory.base.BasicMemory

object ManageRam {
    val ram = Ram()
}

class Ram: BasicMemory(
    UByteArray(4096)
) {
    private val memory = UByteArray(4096)  // 4KB RAM

    override fun read(address: Int): UByte = memory[address]

    override fun write(address: Int, byte: UByte) {
        bytes[address] = byte
    }
}
