package emulator.architecture

import emulator.getBinary
import emulator.getPathToBinary

class Emulator {
    private val cpu = CPU() // for executing prog

    fun run(programPath: String? = null) {
        var pathToBinaryFile = programPath ?: getPathToBinary()
    }

        try {
            val file = getBinary(pathToBinaryFile)
            val binaryFile =  getBinary()(getPathToBinary())

            executor.executeProgram(rom)
        } catch (e: IOException) {
            println("error IOException")
        } catch (e: IllegalArguementException)
        {
            println("error IllegalArgEx")
        } catch (e: Exception) {
            println("unexpected error")
        }

}