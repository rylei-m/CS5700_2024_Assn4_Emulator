package emulator.architecture.instructions.set

import emulator.architecture.Utili
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.ManageP.p
import emulator.architecture.registers.RX
import emulator.architecture.registers.RXManager.r

class SkipNotEqual(
    nibbles: ByteArray
) : Instruction(nibbles) {

    var shouldSkip = false

    lateinit var x: RX
    lateinit var y: RX

    override fun processNibbles() {
        val xValue = nibbles[0].toInt()
        val yValue = nibbles[1].toInt()
        x = r[xValue]
        y= r[yValue]
    }

    override fun performOperation() {
        val xValue = x.read()[0].toInt()
        val yValue = y.read()[0].toInt()

        shouldSkip = (xValue != yValue)
    }

    override fun incrementProgramCounter() {
        val currentBI = Utili().byteArrayToInt(p.read())
        val offset = if (shouldSkip) 4 else 2
        val newBI = currentBI + offset
        p.write(Utili().intToByteArray(newBI))
    }
}