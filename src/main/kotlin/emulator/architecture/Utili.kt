package emulator.architecture

@OptIn(ExperimentalUnsignedTypes::class)
class Utili {

    fun breakByteIntoNibbles(byte: UByte): Pair<UByte, UByte> {
        val unsignedByte = byte.toUByte().toInt()
        val highNibble = (unsignedByte shr 4) and 0x0F
        val lowNibble = unsignedByte and 0x0F
        return Pair(highNibble.toUByte(), lowNibble.toUByte())
    }

    fun byteArrayToInt(byteArray: UByteArray): Int {
        require(byteArray.size == 2) { "Byte array must be of size 2" }
        val result = (byteArray[1].toInt() and 0xFF) or ((byteArray[0].toUByte().toInt() and 0xFF) shl 8)
        return result.toUByte().toInt()
    }

    fun intToByteArray(newBI: Int): UByteArray {
        require(newBI in 0..0xFFFF) {}
        val byteArray = UByteArray(2)
        byteArray[0] = ((newBI shr 8) and 0xFF).toUByte()
        byteArray[1] = (newBI and 0xFF).toUByte()
        return byteArray.toUByteArray()
    }
    fun combineNibblesToByte(highNibble: UByte, lowNibble: UByte): UByte {
        val highNibbleInt = (highNibble.toUByte().toInt() and 0x0F)
        val lowNibbleInt = (lowNibble.toUByte().toInt() and 0x0F)
        val combinedByte = (highNibbleInt shl 4) or lowNibbleInt
        return combinedByte.toUByte()
    }
}