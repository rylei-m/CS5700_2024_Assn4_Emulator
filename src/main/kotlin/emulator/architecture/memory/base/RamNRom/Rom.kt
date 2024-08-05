package emulator.architecture.memory.base.RamNRom

import emulator.architecture.memory.Memory
import emulator.architecture.memory.base.BasicMemory

object ManageRom {
    var rom: Rom? = null

    fun initializeRom(bytes: ByteArray) {
        rom = Rom(bytes)
    }

    fun fetchRom(): Rom? {
        return rom
    }
}


class Rom(
    bytes: ByteArray
): BasicMemory(bytes) {

    private val memory = ByteArray(4096)  // 4KB ROM

    override fun read(address: Int): Byte {
        return bytes[address]
    }

    override fun write(address: Int, byte: Byte) {
        throw UnsupportedOperationException("ROM is read-only")
    }
}