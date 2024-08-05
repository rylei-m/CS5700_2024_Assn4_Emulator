package emulator.architecture.registers

object RXManager {
    val r = arrayOf(R(),R(),R(),R(),R(),R(),R(),R())
}

class RX : Register(
    ByteArray(1)
){
    override fun write(bytes: ByteArray) {
        TODO("Not yet implemented")
    }
}