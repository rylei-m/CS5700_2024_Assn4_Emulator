package emulator.architecture.memory.base.RamNRom

import emulator.architecture.memory.Memory
import emulator.architecture.memory.base.BasicMemory

object ManageRam {
    val ram = Ram()
}

class Ram: BasicMemory() {
    private val memory = ByteArray(4096)  // 4KB RAM

    override fun read(address: Int): Byte = memory[address]

    override fun write(address: Int, value: Byte) {
        memory[address] = value
    }
}