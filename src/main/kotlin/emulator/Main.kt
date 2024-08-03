package org.example

import emulator.architecture.Screen
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val cpu = CPU()
    val screen = Screen()

    val programData = loadProgram()
    cpu.loadProgram(programData)

    val executor = Executors.newSingleThreadScheduledExecutor()
    val cpuRunnable = Runnable {
        cpu.executeInstruction()
        cpu.timer.value = (cpu.timer.value -1).toByte()
        screen.updateScreen()
    }

    val cpuFuture = executor.scheduleAtFixedRate(
        cpuRunnable,
        0,
        1000L / 500L,
        TimeUnit.MILLISECONDS
    )

    Runtime.getRuntime().addShutdownHook(Thread {
        cpuFuture.cancel(true)
        executor.shutdown()
    })

}

fun loadProgram(): ByteArray {
    print("enter the path to the program file: ")
    val path = readLine() ?: throw IllegalArgumentException("Path cannot be null")
    return File(path).readBytes()
}