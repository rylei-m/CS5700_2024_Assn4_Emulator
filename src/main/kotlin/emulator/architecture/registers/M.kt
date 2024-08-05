package emulator.architecture.registers

object ManageA {
    val a = A()
}

class M : Register(
    ByteArray(2)
) {
    override fun write(bytes: ByteArray) {

    }
}