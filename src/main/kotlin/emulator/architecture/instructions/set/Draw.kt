package emulator.architecture.instructions.set

import emulator.architecture.Screen
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.RX
import emulator.architecture.registers.RXManager.r

class Draw(
    nibbles: ByteArray
) : Instruction(nibbles) {

    lateinit var x: RX
    var row: Byte = 0
    var col: Byte = 0

    override fun processNibbles() {
        val xValue = nibbles[0].toInt()
        x = r[xValue]
        row = nibbles[1]
        col = nibbles[2]    }

    override fun preformOperation() {
        val asciiValue = x.read()[0].toInt()

        if (asciiValue > 0x7F) {
            throw IllegalArgumentException()
        }

        Screen().draw(asciiValue.toByte(), row, col)    }
}