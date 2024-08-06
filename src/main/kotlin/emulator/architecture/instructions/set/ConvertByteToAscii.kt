package emulator.architecture.instructions.set

import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.RX
import emulator.architecture.registers.RXManager.r

class ConvertByteToAscii(
    nibbles: ByteArray
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
            (value + '0'.code).toByte()
        } else {
            (value - 10 + 'A'.code).toByte()
        }

        y.write(byteArrayOf(asciiValue))    }
}