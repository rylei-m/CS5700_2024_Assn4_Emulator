package emulator.architecture

import emulator.architecture.fundamentals.Executor
import emulator.architecture.fundamentals.PauseTimer
import emulator.architecture.memory.base.types.Rom
import emulator.architecture.registers.ManageT.t
import emulator.architecture.instructions.InstructionFactory
import emulator.help.Helper

class CPU(
    val timerSpeed: Long = 200L,     //500 times p/sec
    val executeInstructions: Long = 500L
) {
    var rom: Rom? = null

    val cpuRunnable = Runnable {
        try {
            val bytes = Helper().readNextInstructionBytes()
            require(bytes.size == 2)
            if (bytes[0].toInt() == 0 && bytes[1].toInt() == 0) {
                Executor().executor.shutdown()
                return@Runnable
            }
            val nibbles01 = Utili().breakByteIntoNibbles(bytes[0])
            val nibbles23 = Utili().breakByteIntoNibbles(bytes[1])
            val nibble0 = nibbles01.first
            val nibble1 = nibbles01.second
            val nibble2 = nibbles23.first
            val nibble3 = nibbles23.second

        val instruction = InstructionFactory().createInstruction(nibble0,nibble1,nibble2,nibble3)
        instruction.execute()
        } catch (e: Exception) {
            Executor().executor.shutdown()
            return@Runnable
        }
    }
}