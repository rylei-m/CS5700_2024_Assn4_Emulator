package emulator.architecture.instructions.set

import emulator.Facade.ManageM.m
import emulator.architecture.instructions.Instruction

@OptIn(ExperimentalUnsignedTypes::class)
class SwitchMemory(
    nibbles: UByteArray
) : Instruction(nibbles) {
    override fun processNibbles() {
    }

    override fun performOperation() {
        val currentMValue = m.read()[0].toUByte().toInt()
        val newMValue = if (currentMValue == 0) { 1 } else { 0 }
        val newMValueBytes = ubyteArrayOf(newMValue.toUByte())

        m.write(newMValueBytes)
    }
}