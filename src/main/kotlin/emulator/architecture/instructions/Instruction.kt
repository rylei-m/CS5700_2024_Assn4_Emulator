package emulator.architecture.instructions

import emulator.architecture.Utili
import emulator.architecture.registers.ManageP.p
import emulator.Facade

abstract class Instruction(
    val nibbles: UByteArray) {

    init {
        require(nibbles.size == 3) {}
    }

    fun execute(Facade) {
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