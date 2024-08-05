package emulator.architecture

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Executor {
    private val rom: Rom? = null
    private val executor = Executors.newSingleThreadScheduledExecutor()
    fun executeProgram(rom: Rom) {
        this.rom = rom

        val CPUFuture = executor.scheduleAtFixedRate(
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
            timerFuture.get()
            CPUFuture.get()
        } catch (e: Exception) {
            print("error")
        } finally {
            executor.shutdown()
        }

    }

}