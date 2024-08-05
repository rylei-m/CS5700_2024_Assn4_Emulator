package emulator.architecture.registers

import emulator.Utili

object ManageP {
    val p = P()
}

class P : Register(
    ByteArray(2)
) {
    override fun write(bytes: ByteArray) {
        require(bytes.size == 2)
        val intValue = Utili().byteArrayToInt(bytes)
        require(intValue % 2 == 0)
        bytes.copyInto(destination = this.bytes, startIndex = 0, endIndex = 2)
    }
}