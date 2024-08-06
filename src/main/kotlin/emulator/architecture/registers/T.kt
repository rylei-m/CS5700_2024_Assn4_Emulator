package emulator.architecture.registers

@OptIn(ExperimentalUnsignedTypes::class)
class T : Register(
    UByteArray(1)
) {
    override fun write(bytes: UByteArray) {
        require(bytes.size == 1)
        bytes.copyInto(destination = this.bytes, startIndex = 0, endIndex = 1)
    }
}
