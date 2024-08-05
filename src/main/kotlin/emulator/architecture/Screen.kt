package emulator.architecture

class Screen {
companion object {
    const val BUFFER_HEIGHT = 8
    const val BUFFER_WIDTH = 8
}
    private val buffer: ByteArray = ByteArray(BUFFER_HEIGHT* BUFFER_WIDTH)

    private fun display() {
        for (row in 0 until  BUFFER_HEIGHT) {
            for (col in 0 until BUFFER_WIDTH) {
                val index = row * BUFFER_WIDTH + col
                val char = buffer[index].toInt().toChar()
                print(char)
            }
        }
        println("=".repeat(BUFFER_WIDTH))
    }

    fun draw(byte: Byte, row: Byte, col: Byte) {
        val rowIndex = row.toInt()
        val colIndex = col.toInt()
        if (rowIndex in 0 until BUFFER_HEIGHT && colIndex in 0 until BUFFER_WIDTH) {

        } else {
            throw IllegalArgumentException()
        }
    }
}

// The screen is a 8x8 ASCII display. The screen has 64 bytes of internal RAM that serves as the frame buffer for the ASCII character to display at each position.
