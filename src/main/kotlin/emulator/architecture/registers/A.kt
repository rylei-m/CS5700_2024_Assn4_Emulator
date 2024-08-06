package emulator.architecture.registers

object ManageA {
    val a = A()
}
class A : Register(
    UByteArray(2)
) {
    override fun write(bytes: UByteArray) {
        require(bytes.size == 2)
        bytes.copyInto(destination = this.bytes, startIndex = 0, endIndex = 2)
    }
}
