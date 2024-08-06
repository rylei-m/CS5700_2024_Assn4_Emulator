package emulator

import emulator.architecture.CPU
import emulator.architecture.fundamentals.Executor
import emulator.help.Helper
import java.io.IOException

class Emulator {
    private val cpu = CPU() // for executing prog

    fun run() {
        try {
            val pathToBinary = Helper().pathToBinary()
            val binaryFile = Helper().getBinary(pathToBinary)
            val binaryProgram = Helper().binaryProgramFromFile(Helper().getBinary(binaryFile.toString()))
            val rom = Helper().romFromBinaryProgram(binaryProgram)
            Executor().executeProgram(rom)
        } catch (e: IOException) {
            println("error IOException")
        } catch (e: IllegalArgumentException) {
            println("error IllegalArgEx")
        } catch (e: Exception) {
            println("unexpected error")
        }
    }
}