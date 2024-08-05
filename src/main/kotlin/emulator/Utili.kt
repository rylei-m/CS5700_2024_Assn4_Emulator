package emulator

import emulator.architecture.memory.base.RamNRom.ManageRom
import emulator.architecture.memory.base.RamNRom.ManageRom.rom
import emulator.architecture.memory.base.RamNRom.Rom
import emulator.architecture.registers.ManageP.p
import java.io.File
import java.io.IOException

class Utili {
    fun pathToBinary(): String {
        val pathToBinary = readlnOrNull() ?: throw IOException("Cant be Null")
        return pathToBinary
    }

    fun getBinary(pathToBinaryFile: String): File {
        val file = File(pathToBinaryFile)
        if (!file.exists()) {
            throw IOException("File does not exist at path: $pathToBinaryFile")
        }
        return file
    }
    fun byteArrayToInt(byteArray: ByteArray): Int {
        require(byteArray.size == 2) { "Byte array must be of size 2" }
        val result = (byteArray[1].toInt() and 0xFF) or ((byteArray[0].toInt() and 0xFF) shl 8)
        return result
    }

    fun readNextInstructionBytes(): ByteArray {
        return try {
            val bi = byteArrayToInt(p.read())
            val byte1 = rom?.read(bi) ?: 0
            val byte2 = rom?.read(bi + 1) ?: 0
            byteArrayOf(byte1, byte2)
        } catch (e: Exception) {
            byteArrayOf(0, 0)
        }

    }

    fun getPathToBinary(): String {
        val pathToBinary = readlnOrNull() ?: throw IOException("Path cannot be null")
        return pathToBinary
    }



    fun binaryProgramFromFile(binaryFile: File): ByteArray {
        return try {
            binaryFile.readBytes()
        } catch (e:IOException) {
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
    fun breakByteIntoNibbles(byte: Byte): Pair<Byte, Byte> {
        val unsignedByte = byte.toUByte().toInt()
        val highNibble = (unsignedByte shr 4) and 0x0F
        val lowNibble = unsignedByte and 0x0F
        return Pair(highNibble.toByte(), lowNibble.toByte())
    }

    fun intToByteArray(newBI: Int): ByteArray {
        require(newBI in 0..0xFFFF) {}
        val byteArray = ByteArray(2)
        byteArray[0] = ((newBI shr 8) and 0xFF).toByte()
        byteArray[1] = (newBI and 0xFF).toByte()
        return byteArray
    }
    fun combineNibblesToByte(highNibble: Byte, lowNibble: Byte): Byte {
        val highNibbleInt = (highNibble.toInt() and 0x0F)
        val lowNibbleInt = (lowNibble.toInt() and 0x0F)
        val combinedByte = (highNibbleInt shl 4) or lowNibbleInt
        return combinedByte.toByte()
    }
}