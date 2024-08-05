// Josephs Code

import java.io.File

fun main(args: Array<String>) {
    if (args.size < 2) {
        println("Usage: Assembler <inputFile> <outputFile>")
        return
    }

    val bytes = mutableListOf<Byte>()
    val infile = File(args[0])
    val outfile = File(args[1])

    infile.forEachLine {
        if (it.length >= 4) {
            bytes.add(it.substring(0,2).lowercase().toInt(16).toByte())
            bytes.add(it.substring(2,4).lowercase().toInt(16).toByte())
        }
    }
    outfile.writeBytes(bytes.toByteArray())
}