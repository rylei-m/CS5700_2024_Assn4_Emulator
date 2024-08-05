package emulator.architecture.registers

object ManageP {
    val P = P()
}

class P : Register(
    ByteArray(2)
) {
    override fun write(bytes: ByteArray) {

    }
}