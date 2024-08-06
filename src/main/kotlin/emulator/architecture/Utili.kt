package emulator.architecture

import emulator.architecture.memory.base.types.ManageRom
import emulator.architecture.memory.base.types.ManageRom.rom
import emulator.architecture.memory.base.types.Rom
import emulator.architecture.registers.ManageP.p
import java.io.File
import java.io.IOException

class Utili {

    fun breakByteIntoNibbles(byte: Byte): Pair<Byte, Byte> {
        val unsignedByte = byte.toUByte().toInt()
        val highNibble = (unsignedByte shr 4) and 0x0F
        val lowNibble = unsignedByte and 0x0F
        return Pair(highNibble.toByte(), lowNibble.toByte())
    }

    fun byteArrayToInt(byteArray: ByteArray): Int {
        require(byteArray.size == 2) { "Byte array must be of size 2" }
        val result = (byteArray[1].toInt() and 0xFF) or ((byteArray[0].toInt() and 0xFF) shl 8)
        return result
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