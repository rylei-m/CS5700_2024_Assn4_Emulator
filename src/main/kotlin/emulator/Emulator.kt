package emulator

import emulator.architecture.CPU
import emulator.architecture.Screen
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Emulator {
    private val cpu = CPU()
    private val screen = Screen()
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var cpuFuture: java.util.concurrent.ScheduledFuture<*>? = null

    fun run(programPath: String? = null) {
        if (programPath != null) {
            loadProgram(programPath)
        } else {
            println("No program path provided.")
            return
        }

        startEmulator()
    }

    private fun loadProgram(programPath: String) {
        val programData = File(programPath).readBytes()
        cpu.load(programData)
        println("Program loaded successfully.")
    }

    private fun startEmulator() {
        val cpuRunnable = Runnable {
            cpu.executeInstructions()
            cpu.timer.value = (cpu.timer.value - 1).toByte()  // Update timer
            screen.updateScreen()
        }

        cpuFuture = executor.scheduleAtFixedRate(
            cpuRunnable,
            0,
            1000L / 500L, // Execute every 2ms
            TimeUnit.MILLISECONDS
        )

        Runtime.getRuntime().addShutdownHook(Thread {
            cpuFuture?.cancel(true)
            executor.shutdown()
        })
    }
}