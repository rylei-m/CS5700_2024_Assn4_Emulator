package emulator.architecture.instructions.set

import emulator.Facade.RXManager.r
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.RX

@OptIn(ExperimentalUnsignedTypes::class)
class Add(
    nibbles: UByteArray
) : Instruction(nibbles) {
    private lateinit var x: RX
    private lateinit var y: RX
    private lateinit var z: RX

    override fun processNibbles() {
        val xValue = nibbles[0].toUByte().toInt()
        val yValue = nibbles[1].toUByte().toInt()
        val zValue = nibbles[2].toUByte().toInt()

        x = r[xValue]
        y = r[yValue]
        z = r[zValue]
    }
    override fun performOperation() {
        val xValue = x.read()[0].toUByte().toInt()
        val yValue = y.read()[0].toUByte().toInt()

        val result = (xValue + yValue).toUByte()

        z.write(ubyteArrayOf(result.toUByte()))
    }
}