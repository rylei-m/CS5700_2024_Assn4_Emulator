package emulator.architecture.instructions.set

import emulator.architecture.instructions.Instruction
import emulator.architecture.registers.ManageM.m

class SwitchMemory(
    nibbles: ByteArray
) : Instruction(nibbles) {
    override fun processNibbles() {
        TODO("Not yet implemented")
    }

    override fun preformOperation() {
        val currentMValue = m.read()[0].toInt()
        val newMValue = if (currentMValue == 0) { 1 } else { 0 }
        val newMValueBytes = byteArrayOf(newMValue.toByte())

        m.write(newMValueBytes)    }
}