package emulator.architecture.instructions.set

import emulator.Utili
import emulator.architecture.instructions.Instruction
import emulator.architecture.memory.base.RamNRom.ManageRam.ram
import emulator.architecture.memory.base.RamNRom.ManageRom
import emulator.architecture.registers.ManageA.a
import emulator.architecture.registers.ManageM.m
import emulator.architecture.registers.RX
import emulator.architecture.registers.RXManager.r

class Read(
    nibbles: ByteArray
) : Instruction(nibbles) {
    lateinit var x: RX

    override fun processNibbles() {
        val xValue = nibbles[0].toInt()
        x = r[xValue]    }

    override fun preformOperation() {
        val mByteArray = m.read()
        val isUsingROM = mByteArray[0].toInt() != 0

        val addressBytes = a.read()
        val address = Utili().byteArrayToInt(addressBytes)

        val value = if (isUsingROM) {
            ManageRom.fetchRom()!!.read(address)
        } else {
            ram.read(address)
        }

        x.write(byteArrayOf(value))    }
}