package emulator.architecture.instructions.set

import emulator.Computer.ManageA.a
import emulator.Computer.ManageM.m
import emulator.Computer.RXManager.r
import emulator.architecture.Utili
import emulator.architecture.instructions.Instruction
import emulator.architecture.memory.base.types.ManageRam.ram
import emulator.architecture.memory.base.types.ManageRom
import emulator.architecture.registers.RX

@OptIn(ExperimentalUnsignedTypes::class)
class Read(
    nibbles: UByteArray
) : Instruction(nibbles) {
    lateinit var x: RX

    override fun processNibbles() {
        val xValue = nibbles[0].toUByte().toInt()
        x = r[xValue]    }

    override fun performOperation() {
        val mByteArray = m.read()
        val isUsingROM = mByteArray[0].toInt() != 0

        val addressBytes = a.read()
        val address = Utili().byteArrayToInt(addressBytes)

        val value = if (isUsingROM) {
            ManageRom.fetchRom()!!.read(address)
        } else {
            ram.read(address)
        }

        x.write(ubyteArrayOf(value))    }
}