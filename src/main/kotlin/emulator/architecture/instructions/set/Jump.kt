package emulator.architecture.instructions.set

import emulator.architecture.Utili
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.ManageP.p

class Jump(
    nibbles: ByteArray
) : Instruction(nibbles) {
    private lateinit var addressBytes: ByteArray
    override fun processNibbles() {
        val highNibble = nibbles[0].toUByte().toInt()
        val middleNibble = nibbles[1].toUByte().toInt()
        val lowNibble = nibbles[2].toUByte().toInt()

        val address = (highNibble shl 8) or (middleNibble shl 4) or lowNibble
        addressBytes = Utili().intToByteArray(address)    }

    override fun performOperation() {
        p.write(addressBytes)
    }

    override fun incrementProgramCounter() {
    }
}