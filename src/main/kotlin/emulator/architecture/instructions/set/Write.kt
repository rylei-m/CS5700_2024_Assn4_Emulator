package emulator.architecture.instructions.set

import emulator.Facade.ManageA.a
import emulator.Facade.ManageM.m
import emulator.Facade.RXManager.r
import emulator.architecture.Utili
import emulator.architecture.instructions.Instruction
import emulator.architecture.memory.base.types.ManageRam.ram
import emulator.architecture.memory.base.types.ManageRom
import emulator.architecture.registers.RX

@OptIn(ExperimentalUnsignedTypes::class)
class Write(
    nibbles: UByteArray
) : Instruction(nibbles) {
    lateinit var x: RX

    override fun processNibbles() {
        val xValue = nibbles[0].toUByte().toInt()
        x = r[xValue]
    }

    override fun performOperation() {
        val addressBytes = a.read().toUByteArray()
        val address = Utili().byteArrayToInt(addressBytes).toUByte().toInt()

        val mByteArray = m.read().toUByteArray()
        val isUsingROM = mByteArray[0].toUByte().toInt() != 0

        val value = x.read()[0].toUByte()

        if (isUsingROM) {
            ManageRom.fetchRom()?.let {
                it.write(address, value)
            }
        } else {
            ram.write(address, value)
        }
    }
}