package emulator.architecture.instructions.set

import emulator.Facade.ManageT.t
import emulator.Facade.RXManager.r
import emulator.architecture.fundamentals.PauseTimer
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.RX

@OptIn(ExperimentalUnsignedTypes::class)
class ReadT(
    nibbles: UByteArray
) : Instruction(nibbles) {
    lateinit var x: RX

    override fun processNibbles() {
        val xValue = nibbles[0].toUByte().toInt()
        x = r[xValue]
    }

    override fun performOperation() {
        PauseTimer.pauseTimer.set(true)

        val tValue = t.read()[0]

        x.write(ubyteArrayOf(tValue))

        PauseTimer.pauseTimer.set(false)
    }
}