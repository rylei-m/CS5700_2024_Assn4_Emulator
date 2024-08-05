package emulator.architecture.instructions.set

import emulator.Utili
import emulator.architecture.PauseTimer
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.ManageT.t

class SetT(
    nibbles: ByteArray
) : Instruction(nibbles) {

    var value: Byte = 0

    override fun processNibbles() {
        val highNibble = nibbles[0]
        val lowNibble = nibbles[1]

        value = Utili().combineNibblesToByte(highNibble, lowNibble)
    }

    override fun performOperation() {
        PauseTimer.pauseTimer.set(true)

        t.write(byteArrayOf(value))

        PauseTimer.pauseTimer.set(false)
    }
}