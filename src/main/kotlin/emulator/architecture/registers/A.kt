package emulator.architecture.registers

object ManageA {
    val a = A()
}
class A : Register(
    ByteArray(2)
) {
    override fun write(bytes: ByteArray) {
        require(bytes.size == 2)
        bytes.copyInto(destination = this.bytes, startIndex = 0, endIndex = 2)
    }
}
