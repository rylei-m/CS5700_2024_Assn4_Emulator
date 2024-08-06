package emulator.architecture.fundamentals

import emulator.architecture.CPU
import emulator.architecture.memory.base.types.Rom
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Executor {
    private val rom: Rom? = null
    val executor = Executors.newSingleThreadScheduledExecutor()
    private val instructionSpeed: Long = 2L
    val timerSpeed: Long = 16L

    fun executeProgram(rom: Rom) {
        val cpuFuture = executor.scheduleAtFixedRate(
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
            cpuFuture.get()
        } catch (e: Exception) {
            print("error")
        } finally {
            executor.shutdown()
        }

    }

}