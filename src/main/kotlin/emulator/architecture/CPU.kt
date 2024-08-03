package emulator.architecture

import emulator.Timer
import emulator.architecture.memory.RamNRom.Ram
import emulator.architecture.memory.RamNRom.Rom
import sun.jvm.hotspot.debugger.Address

class CPU {
    val registers: Array<Byte> = ByteArray(8) {0.toByte() }
    val programCounter = ProgramCounter()
    val timer = Timer()
    val address = Address()
    val memoryFlag = MemoryFlag()

    private val ram = Ram()
    private val rom = Rom()

    fun load(program: ByteArray) {
        rom.load(program)
        programCounter.value = 0
    }

    fun executeInstructions() {
        val instruction = readInstructions()
        val opcode = (instruction[0].toInt() and 0xF0) > > 4
        val operand = instruction[1]

        when (opcode) {
            // TODO: instructions
            else -> {
                throw IllegalArgumentException("Unsupported opcode: $opcode")
            }
        }
        programCounter.value = (programCounter.value + 2) % 0x10000
    }

    private fun readInstructions(): ByteArray {
        val address = programCounter.value
        return byteArrayOf(rom.read(address.toInt()), rom.read((address + 1).toInt()))
    }
}

/*
The computer executes instructions at 500hz (500 times per second)
The computer reads 2 bytes from ROM every cycle at P and P + 1 and gives them to the CPU to execute. These bytes form the instruction.
This means the P should always be an even number; operations that increment the program counter should increment it by 2
 */