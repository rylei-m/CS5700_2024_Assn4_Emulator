package emulator

import emulator.architecture.CPU
//import emulator.architecture.fundamentals.Executor
import emulator.help.Helper
import java.io.IOException

class Emulator {
    //private val cpu = CPU() // for executing prog
    private val facade = Facade()

    fun run() {
        try {
            val pathToBinary = facade.helper.pathToBinary()
            facade.loadAndRunProgram(pathToBinary)
            val binaryFile = Helper().getBinary(pathToBinary)
            val binaryProgram = Helper().binaryProgramFromFile(Helper().getBinary(binaryFile.toString()))
            val rom = Helper().romFromBinaryProgram(binaryProgram)
            println(binaryFile)
            CPU().executeProgram(rom)
        } catch (e: IOException) {
            println("error IOException")
        } catch (e: IllegalArgumentException) {
            println("error IllegalArgEx")
        } catch (e: Exception) {
            println("unexpected error")
        }
    }
}