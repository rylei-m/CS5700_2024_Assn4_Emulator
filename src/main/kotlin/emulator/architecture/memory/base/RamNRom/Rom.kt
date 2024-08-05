package emulator.architecture.memory.base.RamNRom

import emulator.architecture.memory.Memory
import emulator.architecture.memory.base.BasicMemory

object ManageRom {
    private var rom: Rom? = null

    fun initializeRom(bytes: ByteArray) {
        rom = Rom(bytes)
    }

    fun getRom(): Rom {
        return rom
    }
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