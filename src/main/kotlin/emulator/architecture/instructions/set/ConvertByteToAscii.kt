package emulator.architecture.instructions.set

import emulator.Facade.RXManager.r
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.RX

@OptIn(ExperimentalUnsignedTypes::class)
class ConvertByteToAscii(
    nibbles: UByteArray
) : Instruction(nibbles) {

    lateinit var x: RX
    lateinit var y: RX
    override fun processNibbles() {
        val rxIndex = nibbles[0].toUByte().toInt()
        val ryIndex = nibbles[1].toUByte().toInt()

        x = r[rxIndex]
        y = r[ryIndex]    }

    override fun performOperation() {
        val value = x.read()[0].toInt()

        require(value <= 0xF) {}

        val asciiValue = if (value < 10) {
            (value + '0'.code).toUByte()
        } else {
            (value - 10 + 'A'.code).toUByte()
        }

        y.write(ubyteArrayOf(asciiValue.toUByte()))    }
}