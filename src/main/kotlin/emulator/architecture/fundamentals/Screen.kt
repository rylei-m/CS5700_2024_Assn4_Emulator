package emulator.architecture.fundamentals

@OptIn(ExperimentalUnsignedTypes::class)
class Screen {
companion object {
    const val BUFFER_HEIGHT = 8
    const val BUFFER_WIDTH = 8
}
    private val buffer: UByteArray = UByteArray(BUFFER_HEIGHT * BUFFER_WIDTH)

    private fun display() {
        for (row in 0 until  BUFFER_HEIGHT) {
            for (col in 0 until BUFFER_WIDTH) {
                val index = row * BUFFER_WIDTH + col
                val char = buffer[index].toUByte().toInt().toChar()
                print(char)
            }
            println()
        }
        println("=".repeat(BUFFER_WIDTH))
    }

    fun draw(byte: UByte, row: UByte, col: UByte) {
        val rowIndex = row.toInt()
        val colIndex = col.toInt()
        if (rowIndex in 0 until BUFFER_HEIGHT && colIndex in 0 until BUFFER_WIDTH) {
            buffer[rowIndex * BUFFER_WIDTH + colIndex] = byte
        } else {
            throw IllegalArgumentException()
        }
    }
}
