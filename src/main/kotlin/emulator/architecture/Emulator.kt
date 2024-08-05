package emulator.architecture

import emulator.architecture.memory.base.RamNRom.ManageRom.rom
import emulator.Utili
import java.io.IOException

class Emulator {
    private val cpu = CPU() // for executing prog

    fun run(programPath: String? = null) {
        var pathToBinary = filePath

        try {
            val file = getBinary(pathToBinary)
            val binaryFile = getBinary(pathToBinary)
            val binaryProgram = binaryProgramFromFile(binaryFile)
            val rom = romFromBinaryProgram(binaryProgram)
            executeProgram(rom)
        } catch (e: IOException) {
            println("error IOException")
        } catch (e: IllegalArguementException) {
            println("error IllegalArgEx")
        } catch (e: Exception) {
            println("unexpected error")
        }
    }
}