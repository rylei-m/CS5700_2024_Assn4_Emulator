package emulator.architecture.instructions.set

import emulator.Computer.RXManager.r
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.RX

@OptIn(ExperimentalUnsignedTypes::class)
class Sub(
    nibbles: UByteArray
) : Instruction(nibbles) {
    lateinit var x: RX
    lateinit var y: RX
    lateinit var z: RX
    override fun processNibbles() {
        val xValue = nibbles[0].toUByte().toInt()
        val yValue = nibbles[1].toUByte().toInt()
        val zValue = nibbles[2].toUByte().toInt()

        x = r[xValue]
        y = r[yValue]
        z = r[zValue]    }

    override fun performOperation() {
        val xValue = x.read()[0].toUByte().toInt()
        val yValue = y.read()[0].toUByte().toInt()

        val result = (xValue - yValue).toByte()

        z.write(ubyteArrayOf(result.toUByte()))    }
}