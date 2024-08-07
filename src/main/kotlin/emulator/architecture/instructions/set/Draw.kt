package emulator.architecture.instructions.set

import emulator.Computer.ManageScreen.screen
import emulator.Computer.RXManager.r
import emulator.architecture.fundamentals.Screen
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.RX

class Draw(
    nibbles: UByteArray
) : Instruction(nibbles) {

    lateinit var x: RX
    var row: UByte = 0u
    var col: UByte = 0u

    override fun processNibbles() {
        val xValue = nibbles[0].toUByte().toInt()
        x = r[xValue]
        row = nibbles[1]
        col = nibbles[2]
    }

    override fun performOperation() {
        val asciiValue = x.read()[0].toUByte().toInt()

        if (asciiValue > 0x7F) {
            throw IllegalArgumentException()
        }

        screen.draw(asciiValue.toUByte(), row, col)    }
}