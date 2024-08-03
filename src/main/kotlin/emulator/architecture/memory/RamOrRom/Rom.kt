package emulator.architecture.memory.RamOrRom

class Rom: Memory {
    private val memory = ByteArray(4096)

    override fun read(address: Int, value: Byte) {
        throw UnsupportedOperationException("Rom Is Read-Only")
    }

    fun load(data: ByteArray) {
        data.copyInto(memory)
    }
}