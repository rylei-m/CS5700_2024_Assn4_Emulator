package emulator.architecture.instructions.set

import emulator.Computer.ManageA.a
import emulator.Computer.ManageM.m
import emulator.Computer.RXManager.r
import emulator.architecture.Utili
import emulator.architecture.instructions.Instruction
import emulator.architecture.memory.base.types.ManageRam.ram
import emulator.architecture.memory.base.types.ManageRom
import emulator.architecture.registers.RX

class ConvertToBase10(
    nibbles: UByteArray
) : Instruction(nibbles) {
    lateinit var x: RX
    override fun processNibbles() {
        val xValue = nibbles[0].toUByte().toInt()
        x = r[xValue]    }

    override fun performOperation() {
        val address = Utili().byteArrayToInt(a.read())
        val value = x.read()[0].toUByte().toInt()
        val hundreds = value / 100
        val tens = (value % 100) /10
        val ones = value % 10
        val myByteArray = m.read()
        val isUsingROM = myByteArray[0].toUByte().toInt() !=0

        if (isUsingROM) {
            ManageRom.fetchRom()!!.write(address, hundreds.toUByte())
            ManageRom.fetchRom()!!.write(address + 1, tens.toUByte())
            ManageRom.fetchRom()!!.write(address + 2, ones.toUByte())
        } else {
            ram.write(address, hundreds.toUByte())
            ram.write(address + 1, tens.toUByte())
            ram.write(address + 2, ones.toUByte())
        }
    }
}