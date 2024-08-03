package emulator.architecture

import emulator.architecture.memory.base.RamNRom.Rom


class CPU(
    val timerSpeed: Long = 500L,     //500 times p/sec
    val executeInstructions: Long = 500L
) {
    private var rom: Rom? = null

    val CPURunnable = Runnable {
        try {
            val bytes = readNextInstructionBytes()
            //TODO: Byte Logic

            return@Runnable
        } catch (e: Exception) {
            executor.shutdown()
            return@Runnable
        }
    }

    private fun readNextInstructionBytes(): ByteArray {
        return try {
            val byte1 = 1
            val byte2 = 1
            byteArrayOf(byte1, byt2)
        } catch (e: Exception) {
            byteArrayOf(0,0)
        }

    }
}


/*
The computer executes instructions at 500hz (500 times per second)
The computer reads 2 bytes from ROM every cycle at P and P + 1 and gives them to the CPU to execute. These bytes form the instruction.
This means the P should always be an even number; operations that increment the program counter should increment it by 2
 */