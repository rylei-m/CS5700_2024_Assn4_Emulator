package emulator.help

import emulator.architecture.Utili
import emulator.architecture.memory.base.types.ManageRom
import emulator.architecture.memory.base.types.ManageRom.rom
import emulator.architecture.memory.base.types.Rom
import emulator.architecture.registers.ManageP.p
import java.io.File
import java.io.IOException

class Helper {

    fun getBinary(pathToBinaryFile: String): File {
        val file = File(pathToBinaryFile)
        if (!file.exists()) {
            throw IOException("File does not exist at path: $pathToBinaryFile")
        }
        return file
    }

    fun readNextInstructionBytes(): ByteArray {
        return try {
            val bi = Utili().byteArrayToInt(p.read())
            val byte1 = rom?.read(bi) ?: 0
            val byte2 = rom?.read(bi + 1) ?: 0
            byteArrayOf(byte1, byte2)
        } catch (e: Exception) {
            byteArrayOf(0, 0)
        }

    }

    fun pathToBinary(): String {
        val pathToBinary = readlnOrNull() ?: throw IOException("Path cannot be null")
        return pathToBinary
    }



    fun binaryProgramFromFile(binaryFile: File): ByteArray {
        return try {
            binaryFile.readBytes()
        } catch (e: IOException) {
            throw IOException()
        }
    }

    fun romFromBinaryProgram(binaryProgram: ByteArray): Rom {
        val memory = ByteArray(4096)
        for (i in binaryProgram.indices) {
            memory[i] = binaryProgram[i]
        }
        ManageRom.initializeRom(memory)
        val rom = ManageRom.fetchRom()
        if (rom == null) {
            throw IOException()
        }
        return rom
    }

}