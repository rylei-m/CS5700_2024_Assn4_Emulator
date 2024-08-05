package emulator.architecture

import emulator.architecture.memory.base.RamNRom.Rom
import emulator.architecture.registers.ManagerT.t

class CPU(
    val timerSpeed: Long = 500L,     //500 times p/sec
    val executeInstructions: Long = 500L
) {
    private var rom: Rom? = null

    val CPURunnable = Runnable {
        try {
            val bytes = readNextInstructionBytes()
            require(bytes.size == 2)
            if (bytes[0].toInt() == 0 && bytes[1].toInt() == 0) {
                executor.shutdown()
                return@Runnable
            }
            val nibbles01 = breakByteIntoNibbles(bytes[0])
            val nibbles23 = breakByteIntoNibbles(bytes[1])
            val nibble0 = nibbles01.first
            val nibble1 = nibbles01.second
            val nibble2 = nibbles23.first
            val nibble3 = nibbles23.second

            //TODO: Instructions
        } catch (e: Exception) {
            executor.shutdown()
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
    private fun byteArrayToInt(byteArray: ByteArray) : Int {
        require(ByteArray.size == 2)
        val result = (byteArray[1].toInt() and 0xFF) or ((byteArray[0].toInt() and 0xFF) shl 8)
        return result
    }

    private fun readNextInstructionBytes(): ByteArray {
        return try {
            val bi = byteArrayToInt(p.read())
            val byte1 = rom?.read(bi) ?: 0
            val byte2 = rom?.read(bi + 1) ?: 0
            byteArrayOf(byte1, byte2)
        } catch (e: Exception) {
            byteArrayOf(0, 0)
        }

    }
}


/*
The computer executes instructions at 500hz (500 times per second)
The computer reads 2 bytes from ROM every cycle at P and P + 1 and gives them to the CPU to execute. These bytes form the instruction.
This means the P should always be an even number; operations that increment the program counter should increment it by 2
 */