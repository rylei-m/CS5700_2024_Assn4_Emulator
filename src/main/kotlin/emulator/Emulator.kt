package emulator

import java.io.IOException

class Emulator {
    private val computer = Computer()

    fun run() {
        try {
            val pathToBinary = computer.helper.pathToBinary()
            computer.loadAndRunProgram(pathToBinary)
            val binaryFile = computer.helper.getBinary(pathToBinary)
            val binaryProgram = computer.helper.binaryProgramFromFile(computer.helper.getBinary(binaryFile.toString()))
            val rom = computer.helper.romFromBinaryProgram(binaryProgram)
            println(binaryFile)
            computer.cpu.executeProgram(rom)
        } catch (e: IOException) {
            println("error IOException")
        } catch (e: IllegalArgumentException) {
            println("error IllegalArgEx")
        } catch (e: Exception) {
            println("unexpected error")
        }
    }
}