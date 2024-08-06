package emulator.architecture.instructions.set

import emulator.architecture.Utili
import emulator.architecture.instructions.Instruction
import emulator.architecture.memory.base.types.ManageRam.ram
import emulator.architecture.memory.base.types.ManageRom
import emulator.architecture.registers.ManageA.a
import emulator.architecture.registers.ManageM.m
import emulator.architecture.registers.RX
import emulator.architecture.registers.RXManager.r

class Write(
    nibbles: ByteArray
) : Instruction(nibbles) {
    lateinit var x: RX

    override fun processNibbles() {
        val xValue = nibbles[0].toInt()
        x = r[xValue]
    }

    override fun performOperation() {
        val addressBytes = a.read()
        val address = Utili().byteArrayToInt(addressBytes)

        val mByteArray = m.read()
        val isUsingROM = mByteArray[0].toInt() != 0

        val value = x.read()[0]

        if (isUsingROM) {
            ManageRom.fetchRom()?.let {
                it.write(address, value)
            }
        } else {
            ram.write(address, value)
        }
    }
}