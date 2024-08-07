package emulator.architecture.instructions.set

import emulator.Computer.ManageP.p
import emulator.Computer.RXManager.r
import emulator.architecture.Utili
import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.RX

@OptIn(ExperimentalUnsignedTypes::class)
class SkipEqual(
    nibbles: UByteArray
) : Instruction(nibbles) {
    var shouldSkip = false
    lateinit var x: RX
    lateinit var y: RX
    override fun processNibbles() {
        val xValue = nibbles[0].toUByte().toInt()
        val yValue = nibbles[1].toUByte().toInt()
        x = r[xValue]
        y = r[yValue]
    }

    override fun performOperation() {
        val xValue = x.read()[0].toInt()
        val yValue = y.read()[0].toInt()

        shouldSkip = (xValue == yValue)    }

    override fun incrementProgramCounter() {
        val currentBI = Utili().byteArrayToInt(p.read())
        val offset = if (shouldSkip) 4 else 2
        val newBI = currentBI + offset
        p.write(Utili().intToByteArray(newBI))   }
}