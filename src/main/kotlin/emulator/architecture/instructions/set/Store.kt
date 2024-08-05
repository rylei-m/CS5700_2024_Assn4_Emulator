package emulator.architecture.instructions.set

import emulator.Utili
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.RX
import emulator.architecture.registers.RXManager.r

class Store(
    nibbles: ByteArray
) : Instruction(nibbles) {

    lateinit var x: RX
    var byte: Byte = 0

    override fun processNibbles() {
        val xValue = nibbles[0].toInt()
        x = r[xValue]

        val highNibble = nibbles[1]
        val lowNibble = nibbles[2]

        byte = Utili().combineNibblesToByte(highNibble, lowNibble)
    }

    override fun performOperation() {
        x.write(byteArrayOf(byte))
    }
}