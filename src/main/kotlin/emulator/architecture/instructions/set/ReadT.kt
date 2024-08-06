package emulator.architecture.instructions.set

import emulator.architecture.fundamentals.PauseTimer
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.ManageT.t
import emulator.architecture.registers.RX
import emulator.architecture.registers.RXManager.r

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

        x.write(byteArrayOf(tValue))

        PauseTimer.pauseTimer.set(false)
    }
}