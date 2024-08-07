package emulator

import emulator.architecture.registers.*
import emulator.architecture.CPU
import emulator.architecture.Utili
//import emulator.architecture.fundamentals.Executor
import emulator.architecture.fundamentals.Screen
import emulator.help.Helper

class Computer {

    val helper = Helper()
    val cpu = CPU(this)

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