package emulator

import emulator.architecture.CPU
import emulator.architecture.Screen
import java.io.File
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Emulator {
    private val cpu = CPU() // for executing prog
    private val screen = Screen()
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var cpuFuture: java.util.concurrent.ScheduledFuture<*>? = null

    fun run(programPath: String? = null) {
        val pathToBinaryFile = programPath ?: getPathToBinary()    }

    try {
        val file = getBinary(pathToBinaryFile)

    } catch (e: IOException) {
        println("error IOException")
    }

}

private fun getPathToBinary(): String {
    val pathToBinary = readlnOrNull() ?: throw IOException("Path cannot be null")
    return pathToBinary
}

private fun getBinary(pathToBinaryFile: String): File {
    val file = File(pathToBinaryFile)
    if (!file.exists()) {
        throw IOException("File does not exist at path: $pathToBinaryFile")
    }
    return file

}
