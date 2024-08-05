package emulator.architecture.registers

object RXManager {
    val r = arrayOf(RX(),RX(),RX(),RX(),RX(),RX(),RX(),RX())
}

class RX : Register(
    ByteArray(1)
){
    override fun write(bytes: ByteArray) {
        require(bytes.size == 1)
        bytes.copyInto(destination = this.bytes, startIndex = 0, endIndex = 1)
    }
}