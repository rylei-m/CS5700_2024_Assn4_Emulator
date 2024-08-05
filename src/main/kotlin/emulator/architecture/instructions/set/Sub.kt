package emulator.architecture.instructions.set

import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.RX
import emulator.architecture.registers.RXManager.r

class Sub(
    nibbles: ByteArray
) : Instruction(nibbles) {
    lateinit var x: RX
    lateinit var y: RX
    lateinit var z: RX
    override fun processNibbles() {
        val xValue = nibbles[0].toInt()
        val yValue = nibbles[1].toInt()
        val zValue = nibbles[2].toInt()

        x = r[xValue]
        y = r[yValue]
        z = r[zValue]    }

    override fun performOperation() {
        val xValue = x.read()[0].toInt()
        val yValue = y.read()[0].toInt()

        val result = (xValue - yValue).toByte()

        z.write(byteArrayOf(result))    }
}