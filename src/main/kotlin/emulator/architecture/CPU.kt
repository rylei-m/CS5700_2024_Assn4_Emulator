package emulator.architecture

import emulator.Computer
//import emulator.architecture.fundamentals.Executor
import emulator.architecture.fundamentals.timerRunnable
import emulator.architecture.memory.base.types.Rom
import emulator.architecture.instructions.InstructionFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalUnsignedTypes::class)
class CPU(
    private val computer: Computer,
    val timerSpeed: Long = 2L,
    val executeInstructions: Long = 5L,
) {
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private val instructionFactory = InstructionFactory()
    private var rom: Rom? = null
    private val instructionSpeed: Long = 2L

    fun executeProgram(rom: Rom) {
        this.rom = rom

        val cpuFuture = executor.scheduleAtFixedRate(
            cpuRunnable,
            0,
            instructionSpeed,
            TimeUnit.MILLISECONDS
        )

        val timerFuture = executor.scheduleAtFixedRate(
            timerRunnable,
            0,
            timerSpeed,
            TimeUnit.MILLISECONDS
        )

        try {
            cpuFuture.get()
            timerFuture.get()
        } catch (e: Exception) {
            println(e.message)
        } finally {
            executor.shutdown()
        }

    }

    val cpuRunnable = Runnable {
        try {
            val bytes = computer.readNextInstruction()
            require(bytes.size == 2)
            if (bytes[0].toInt() == 0 && bytes[1].toInt() == 0) {
                executor.shutdown()
                return@Runnable
            }
            val nibbles01 = Utili().breakByteIntoNibbles(bytes[0])
            val nibbles23 = Utili().breakByteIntoNibbles(bytes[1])
            val nibble0 = nibbles01.first
            val nibble1 = nibbles01.second
            val nibble2 = nibbles23.first
            val nibble3 = nibbles23.second

            val instruction = instructionFactory.createInstruction(nibble0, nibble1, nibble2, nibble3)
            instruction.execute(computer)
        } catch (e: Exception) {
            e.printStackTrace()
            executor.shutdown()
            return@Runnable
        }
    }
}
