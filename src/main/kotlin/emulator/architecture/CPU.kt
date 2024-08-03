package emulator.architecture

import emulator.Timer
import emulator.architecture.memory.RamNRom.Ram
import emulator.architecture.memory.RamNRom.Rom
import sun.jvm.hotspot.debugger.Address
import java.util.concurrent.TimeUnit

class CPU(
    val timerSpeed: Long = 500L,     //500 times p/sec
    val executeInstructions: Long = 500L
) {
    private var rom: Rom? = null

    private fun readNextInstructionBytes(): ByteArray {

    val CPURunnable = Runnable {
    try {
        val bytes = readNextInstructionBytes()
        //TODO: Byte Logic
    } catch (e: Exception) {
        executor.shutdown()
        return@Runnable
    }
}

}


/*
The computer executes instructions at 500hz (500 times per second)
The computer reads 2 bytes from ROM every cycle at P and P + 1 and gives them to the CPU to execute. These bytes form the instruction.
This means the P should always be an even number; operations that increment the program counter should increment it by 2
 */