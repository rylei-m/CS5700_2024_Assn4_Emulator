package emulator.architecture

import emulator.Facade
//import emulator.architecture.fundamentals.Executor
import emulator.architecture.fundamentals.timerRunnable
import emulator.architecture.memory.base.types.Rom
import emulator.architecture.instructions.InstructionFactory
import emulator.help.Helper
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalUnsignedTypes::class)
class CPU(
    val timerSpeed: Long = 2L,
    val executeInstructions: Long = 5L,
) {
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private val instructionFactory = InstructionFactory()
    private var rom: Rom? = null
    private val instructionSpeed: Long = 2L


    fun executeProgram(rom: Rom) {
        this.rom = rom
        val cpu = CPU()

        val cpuFuture = executor.scheduleAtFixedRate(
            cpu.cpuRunnable,
            0,
            instructionSpeed,
            TimeUnit.MILLISECONDS
        )
        println(cpu)

        val timerFuture = executor.scheduleAtFixedRate(
            timerRunnable,
            0,
            timerSpeed,
            TimeUnit.MILLISECONDS
        )
        println(timerFuture)

        try {
            println("trying cpuFuture")
            cpuFuture.get()
            println("trying timerFuture")
            timerFuture.get()
        } catch (e: Exception) {
            println("error did not work")
            cpuFuture.get()
            timerFuture.get()
        } finally {
            executor.shutdown()
        }

    }

    fun shutdown() {
        try {
            shutdown()
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow()
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    println("Executor did not terminate")
                }
            }
        } catch (e: InterruptedException) {
            executor.shutdownNow()
            Thread.currentThread().interrupt()
        }
    }

    val cpuRunnable = Runnable {
        try {
            val bytes = Helper().readNextInstructionBytes()
            println(bytes)
            require(bytes.size == 2)
            if (bytes[0].toInt() == 0 && bytes[1].toInt() == 0) {
                println("CPU running on $instructionSpeed")
                try {
                    shutdown()
                    println("shutting down")
                } catch (e: Exception) {
                    println("shutdown failed")
                    e.printStackTrace()
                }
                return@Runnable
            }
            val nibbles01 = Utili().breakByteIntoNibbles(bytes[0])
            val nibbles23 = Utili().breakByteIntoNibbles(bytes[1])
            val nibble0 = nibbles01.first
            val nibble1 = nibbles01.second
            val nibble2 = nibbles23.first
            val nibble3 = nibbles23.second

            val instruction = InstructionFactory().createInstruction(nibble0, nibble1, nibble2, nibble3)
            println("go to facade")
            instruction.execute(Facade())
            println("out of Facade")
        } catch (e: Exception) {
            e.printStackTrace()
            executor.shutdown()
            return@Runnable
        }
    }
}
