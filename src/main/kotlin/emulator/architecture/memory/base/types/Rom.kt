package emulator.architecture.memory.base.types

import emulator.architecture.memory.base.BasicMemory

@OptIn(ExperimentalUnsignedTypes::class)
object ManageRom {
    var rom: Rom? = null

    fun initializeRom(bytes: UByteArray) {
        rom = Rom(bytes)
    }

    fun fetchRom(): Rom? {
        return rom
    }
}


@OptIn(ExperimentalUnsignedTypes::class)
class Rom(
    bytes: UByteArray
): BasicMemory(bytes) {

    private val memory = UByteArray(4096)  // 4KB ROM

    override fun read(address: Int): UByte {
        return bytes[address]
    }

    override fun write(address: Int, byte: UByte) {
        throw UnsupportedOperationException("ROM is read-only")
    }
}
