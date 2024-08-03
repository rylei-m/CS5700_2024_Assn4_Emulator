package emulator.architecture.registers

object ManagerT {
    val t = T()
}

class T : Register(
    ByteArray()
) {
    override fun write(bytes: ByteArray) {

    }
}