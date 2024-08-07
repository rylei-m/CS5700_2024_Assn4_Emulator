package emulator.architecture.instructions.set

import emulator.Computer.ManageA.a
import emulator.architecture.Utili
import emulator.architecture.instructions.Instruction

@OptIn(ExperimentalUnsignedTypes::class)
class SetA(
    nibbles: UByteArray
) : Instruction(nibbles) {
    private var addressBytes: UByteArray = UByteArray(0)
    override fun processNibbles() {
        val highNibble = nibbles[0].toUByte().toInt()
        val middleNibble = nibbles[1].toUByte().toInt()
        val lowNibble = nibbles[2].toUByte().toInt()

        val address = (highNibble shl 8) or (middleNibble shl 4) or lowNibble
        addressBytes = Utili().intToByteArray(address)
    }

    override fun performOperation() {
        a.write(addressBytes)
    }
}