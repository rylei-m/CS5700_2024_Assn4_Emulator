package emulator.architecture.instructions

import emulator.architecture.registers.ManageP.P

abstract class Instruction(
    protected val nibbles: ByteArray) {
    init {
        require(nibbles.size == 3) {}
    }

    fun execute() {
        processNibbles()
        preformOperation()
        incrementProgramCounter()
    }

    abstract fun processNibbles()
    abstract fun preformOperation()

    open fun incrementProgramCounter() {
        val currentBI = byteArrayToInt(P.read())
        val newBI = currentBI
        P.write(intToByteArray(newBI))
    }
}