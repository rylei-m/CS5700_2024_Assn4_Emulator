package emulator

import emulator.architecture.registers.*
import emulator.architecture.CPU
import emulator.architecture.Utili
//import emulator.architecture.fundamentals.Executor
import emulator.architecture.fundamentals.Screen
import emulator.architecture.memory.base.types.Rom
import emulator.help.Helper

@OptIn(ExperimentalUnsignedTypes::class)
class Facade {

    fun startEmulator() {
        val pathToBinary = helper.pathToBinary()
        val binaryFile = helper.getBinary(pathToBinary)
        val binaryProgram = helper.binaryProgramFromFile(binaryFile)
        val rom = helper.romFromBinaryProgram(binaryProgram)
        //val executor = Executor()
        cpu.executeProgram(rom)
    //val rom = Rom()
        //val executor = Executor()
        //executor.executeProgram(rom)
    }
    val helper = Helper()
    //private val executor = Executor()
    private val cpu = CPU()

    object ManageA {
        val a = A()
    }

    object ManageM {
        val m = M()
    }

    object ManageP {
        val p = P()
    }

    object RXManager {
        val r = arrayOf(RX(), RX(), RX(), RX(), RX(), RX(), RX(), RX())
    }

    object ManageT {
        val t = T()
    }

    object ManageScreen {
        val screen = Screen()
    }

    fun loadAndRunProgram(pathToBinary: String) {
        try {
            val binaryFile = helper.getBinary(pathToBinary)
            val binaryProgram = helper.binaryProgramFromFile(binaryFile)
            val rom = helper.romFromBinaryProgram(binaryProgram)
            cpu.executeProgram(rom)
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    fun readNextInstruction(): UByteArray {
        return helper.readNextInstructionBytes()
    }

    fun incrementProgramCounter() {
        val currentBI = Utili().byteArrayToInt(ManageP.p.read())
        val newBI = currentBI + 2
        ManageP.p.write(Utili().intToByteArray(newBI))
    }


}