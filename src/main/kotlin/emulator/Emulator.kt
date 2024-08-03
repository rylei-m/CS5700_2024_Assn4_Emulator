package emulator

import emulator.architecture.CPU
import emulator.architecture.Screen
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Emulator {
    private val cpu = CPU() // for executing prog
    private val screen = Screen()
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var cpuFuture: java.util.concurrent.ScheduledFuture<*>? = null

    fun run(programPath: String? = null) {
        //for strating em
    }

    private fun enterFile(): String {
        //TODO: user names the path
        return pathOfFile
    }

}

/*
Probably should have
        check for if the file exists
        file to bytes
        something for rom and ram
*/
