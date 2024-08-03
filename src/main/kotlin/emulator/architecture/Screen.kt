package emulator.architecture

class Screen {
companion object {
    const val BufferHeight = 8
    const val BufferWidth = 8
}
    val buffer: ByteArray = ByteArray(BufferHeight* BufferWidth)
    private fun display() {

    }

    fun draw() {

    }

}

// The screen is a 8x8 ASCII display. The screen has 64 bytes of internal RAM that serves as the frame buffer for the ASCII character to display at each position.
