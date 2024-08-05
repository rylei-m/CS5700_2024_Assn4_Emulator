package emulator

import java.io.File
import java.io.IOException

private fun getPathToBinary(): String {
    val pathToBinary = readlnOrNull() ?: throw IOException("Path cannot be null")
    return pathToBinary
}

private fun getBinary(pathToBinaryFile: String): File {
    val file = File(pathToBinaryFile)
    if (!file.exists()) {
        throw IOException("File does not exist at path: $pathToBinaryFile")
    }
    return file

}
