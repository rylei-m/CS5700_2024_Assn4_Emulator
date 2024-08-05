package emulator.architecture.instructions.set

import emulator.Utili
import emulator.architecture.instructions.Instruction
import emulator.architecture.memory.base.RamNRom.ManageRam.ram
import emulator.architecture.memory.base.RamNRom.ManageRom
import emulator.architecture.registers.ManageA.a
import emulator.architecture.registers.ManageM.m
import emulator.architecture.registers.RX
import emulator.architecture.registers.RXManager.r

class ConvertToBase10(
    nibbles: ByteArray
) : Instruction(nibbles) {
    lateinit var x: RX
    override fun processNibbles() {
        val xValue = nibbles[0].toInt()
        x = r[xValue]    }

    override fun performOperation() {
        val address = Utili().byteArrayToInt(a.read())
        val value = x.read()[0].toInt()
        val hundreds = value / 100
        val tens = (value % 100) /10
        val ones = value % 10
        val myByteArray = m.read()
        val isUsingROM = myByteArray[0].toInt() !=0

        if (isUsingROM) {
            ManageRom.fetchRom()!!.write(address, hundreds.toByte())
            ManageRom.fetchRom()!!.write(address + 1, tens.toByte())
            ManageRom.fetchRom()!!.write(address + 2, ones.toByte())
        } else {
            ram.write(address, hundreds.toByte())
            ram.write(address + 1, tens.toByte())
            ram.write(address + 2, ones.toByte())
        }
    }
}