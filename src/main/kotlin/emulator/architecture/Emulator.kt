package emulator.architecture

import emulator.architecture.memory.base.RamNRom.ManageRom.rom
import emulator.Utili
import java.io.File
import java.io.IOException

class Emulator {
    private val cpu = CPU() // for executing prog

    fun run(programPath: String? = null) {
        var pathToBinary = programPath

        try {
            if (pathToBinary == null) {
                pathToBinary = Utili().getPathToBinary()
            }
            val file = File(pathToBinary)
            val binaryProgram = Utili().binaryProgramFromFile(file)
            val rom = Utili().romFromBinaryProgram(binaryProgram)
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