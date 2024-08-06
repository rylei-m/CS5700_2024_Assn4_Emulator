package emulator.help

import emulator.Facade.ManageP.p
import emulator.architecture.Utili
import emulator.architecture.memory.base.types.ManageRom
import emulator.architecture.memory.base.types.ManageRom.rom
import emulator.architecture.memory.base.types.Rom
import java.io.File
import java.io.IOException

@OptIn(ExperimentalUnsignedTypes::class)
class Helper {

    fun getBinary(pathToBinaryFile: String): File {
        val file = File(pathToBinaryFile)
        if (!file.exists()) {
            throw IOException("File does not exist at path: $pathToBinaryFile")
        }
        return file
    }

    fun readNextInstructionBytes(): UByteArray {
        return try {
            val bi = Utili().byteArrayToInt(p.read())
            val byte1 = rom?.read(bi) ?: 0
            val byte2 = rom?.read(bi + 1) ?: 0
            ubyteArrayOf(byte1 as UByte, byte2 as UByte).toUByteArray()
        } catch (e: Exception) {
            ubyteArrayOf(0u, 0u)
        }

    }

    fun pathToBinary(): String {
        val pathToBinary = readlnOrNull() ?: throw IOException("Path cannot be null")
        return pathToBinary
    }



    fun binaryProgramFromFile(binaryFile: File): UByteArray {
        return try {
            binaryFile.readBytes().toUByteArray()
        } catch (e: IOException) {
            throw IOException()
        }
    }

    fun romFromBinaryProgram(binaryProgram: UByteArray): Rom {
        val memory = UByteArray(4096)
        for (i in binaryProgram.indices) {
            memory[i] = binaryProgram[i]
        }
        ManageRom.initializeRom(memory)
        val rom = ManageRom.fetchRom() ?: throw IOException()
        return rom
    }

}