package emulator.architecture.instructions

import emulator.architecture.Utili
import emulator.architecture.registers.ManageP.p

abstract class Instruction(
    val nibbles: ByteArray) {

    init {
        require(nibbles.size == 3) {}
    }

    fun execute() {
        processNibbles()
        performOperation()
        incrementProgramCounter()
    }

    abstract fun processNibbles()
    abstract fun performOperation()

    open fun incrementProgramCounter() {
        val currentBI = Utili().byteArrayToInt(p.read())
        val newBI = currentBI + 2
        p.write(Utili().intToByteArray(newBI))
    }
}