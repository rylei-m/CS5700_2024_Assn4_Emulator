package emulator.architecture.registers

import emulator.architecture.Utili

@OptIn(ExperimentalUnsignedTypes::class)
class P : Register(
    UByteArray(2)
) {
    override fun write(bytes: UByteArray) {
        require(bytes.size == 2)
        val intValue = Utili().byteArrayToInt(bytes)
        require(intValue % 2 == 0)
        bytes.copyInto(destination = this.bytes, startIndex = 0, endIndex = 2)
        println(bytes + "from P")

    }
}
