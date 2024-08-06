package emulator.architecture.instructions.set

import emulator.architecture.Utili
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.RX
import emulator.architecture.registers.RXManager.r

class Store(
    nibbles: UByteArray
) : Instruction(nibbles) {

    lateinit var x: RX
    var byte: UByte = 0u

    override fun processNibbles() {
        val xValue = nibbles[0].toUByte().toInt()
        x = r[xValue]

        val highNibble = nibbles[1].toUByte()
        val lowNibble = nibbles[2].toUByte()

        byte = Utili().combineNibblesToByte(highNibble, lowNibble)
    }

    override fun performOperation() {
        x.write(ubyteArrayOf(byte))
    }
}