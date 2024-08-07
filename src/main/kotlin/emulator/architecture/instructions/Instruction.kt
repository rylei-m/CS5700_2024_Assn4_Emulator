package emulator.architecture.instructions

import emulator.Computer
import emulator.architecture.Utili
import emulator.Computer.ManageP.p

@OptIn(ExperimentalUnsignedTypes::class)
abstract class Instruction(
    val nibbles: UByteArray) {

    init {
        require(nibbles.size == 3) {}
    }

    fun execute(computer: Computer) {
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