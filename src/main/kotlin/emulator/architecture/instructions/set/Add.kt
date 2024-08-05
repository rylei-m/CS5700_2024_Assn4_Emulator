package emulator.architecture.instructions.set

import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.RX
import emulator.architecture.registers.RXManager.r

class Add(
    nibbles: ByteArray
) : Instruction(nibbles) {
    private lateinit var x: RX
    private lateinit var y: RX
    private lateinit var z: RX

    override fun processNibbles() {
        val xValue = nibbles[0].toInt()
        val yValue = nibbles[1].toInt()
        val zValue = nibbles[2].toInt()

        x = r[xValue]
        y = r[yValue]
        z = r[zValue]
    }
    override fun performOperation() {
        val xValue = x.read()[0].toInt()
        val yValue = y.read()[0].toInt()

        val result = (xValue + yValue).toByte()

        z.write(byteArrayOf(result))
    }
}