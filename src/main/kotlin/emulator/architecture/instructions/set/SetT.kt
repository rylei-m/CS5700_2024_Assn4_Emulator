package emulator.architecture.instructions.set

import emulator.Computer.ManageT.t
import emulator.architecture.Utili
import emulator.architecture.fundamentals.PauseTimer
import emulator.architecture.instructions.Instruction

@OptIn(ExperimentalUnsignedTypes::class)
class SetT(
    nibbles: UByteArray
) : Instruction(nibbles) {

    private var value: UByte = 0u

    override fun processNibbles() {
        val highNibble = nibbles[0].toUByte()
        val lowNibble = nibbles[1].toUByte()

        value = Utili().combineNibblesToByte(highNibble, lowNibble)
    }

    override fun performOperation() {
        PauseTimer.pauseTimer.set(true)

        t.write(ubyteArrayOf(value))

        PauseTimer.pauseTimer.set(false)
    }
}