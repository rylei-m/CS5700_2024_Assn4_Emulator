package emulator.architecture.memory.base.RamNRom

import emulator.architecture.memory.Memory
import emulator.architecture.memory.base.BasicMemory

object ManageRom {
    val rom = Rom()

    // TODO: Other rom things
}

class Rom: BasicMemory(
    ByteArray(4096)
) {
    private val memory = ByteArray(4096)  // 4KB ROM

    override fun read(address: Int): Byte = memory[address]

    override fun write(address: Int, value: Byte) {
        throw UnsupportedOperationException("ROM is read-only")
    }

    fun load(data: ByteArray) {
        data.copyInto(memory)
    }
}