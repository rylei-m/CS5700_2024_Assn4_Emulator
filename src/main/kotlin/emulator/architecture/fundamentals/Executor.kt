package emulator.architecture.fundamentals

import emulator.architecture.CPU
import emulator.architecture.memory.base.types.Rom
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Executor {
    private var rom: Rom? = null
    val executor = Executors.newSingleThreadScheduledExecutor()
    private val instructionSpeed: Long = 2L
    val timerSpeed: Long = 16L

    fun executeProgram(rom: Rom) {
        this.rom = rom
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
            cpuFuture.get()
            timerFuture.get()
        } catch (e: Exception) {
            print("error")
        } finally {
            executor.shutdown()
        }

    }

}