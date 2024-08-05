package emulator

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
            throw IOException("file does not exist")
        }
        return file
    }

}