package emulator.architecture.registers

object ManageT {
    val t = T()
}

class T : Register(
    ByteArray(1)
) {
    override fun write(bytes: ByteArray) {

    }
}