package emulator.architecture

import emulator.architecture.memory.base.RamNRom.Rom
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Executor {
    private val rom: Rom? = null
    val executor = Executors.newSingleThreadScheduledExecutor()

    fun executeProgram(rom: Rom) {
        this.rom = rom
        val executor = Executors.newSingleThreadScheduledExecutor()

        val CPUFuture = executor.scheduleAtFixedRate(
            CPU().CPURunnable,
            0,
            executeInstructions,
            TimeUnit.MILLISECONDS
        )

        val timerFuture = executor.scheduleAtFixedRate(
            timerRunnable,
            0,
            Timer().timerSpeed,
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