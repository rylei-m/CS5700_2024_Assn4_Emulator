package emulator.architecture

import emulator.Utili
import emulator.architecture.instructions.Instruction
import emulator.architecture.memory.base.RamNRom.Rom
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Executor {
    private val rom: Rom? = null
    val executor = Executors.newSingleThreadScheduledExecutor()
    private val instructionSpeed: Long = 2L
    val timerSpeed: Long = 16L

    fun executeProgram(rom: Rom) {
        val CPUFuture = executor.scheduleAtFixedRate(
            CPU().cpuRunnable,
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
            timerFuture.get()
            CPUFuture.get()
        } catch (e: Exception) {
            print("error")
        } finally {
            executor.shutdown()
        }

    }

}