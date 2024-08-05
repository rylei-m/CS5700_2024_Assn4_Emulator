package emulator.architecture.registers

object RXManager {
    val r = arrayOf(RX(),RX(),RX(),RX(),RX(),RX(),RX(),RX())
}

class RX : Register(
    ByteArray(1)
){
    override fun write(bytes: ByteArray) {
        TODO("Not yet implemented")
    }
}