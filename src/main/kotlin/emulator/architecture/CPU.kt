package emulator.architecture

import emulator.Utili
import emulator.architecture.memory.base.RamNRom.Rom
import emulator.architecture.registers.ManageT.t
import emulator.architecture.instructions.InstructionFactory
class CPU(
    val timerSpeed: Long = 500L,     //500 times p/sec
    val executeInstructions: Long = 500L
) {
    var rom: Rom? = null

    val cpuRunnable = Runnable {
        try {
            val bytes = Utili().readNextInstructionBytes()
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

        val instruction = InstructionFactory.createInstruction(nibble0,nibble1,nibble2,nibble3,nibble3)
        instruction.execute()
        } catch (e: Exception) {
            Executor().executor.shutdown()
            return@Runnable
        }
    }

    val timerRunnable = Runnable {
        try {
            if (PauseTimer.pauseTimer.get()) {
                return@Runnable
            }

            val currentTime = t.read()[0].toInt()
            if (currentTime > 0) {
                t.write(byteArrayOf((currentTime -1).toByte()))
            }
        } catch (e: Exception) {
            print("error")
        }
    }

}


/*
The computer executes instructions at 500hz (500 times per second)
The computer reads 2 bytes from ROM every cycle at P and P + 1 and gives them to the CPU to execute. These bytes form the instruction.
This means the P should always be an even number; operations that increment the program counter should increment it by 2
 */