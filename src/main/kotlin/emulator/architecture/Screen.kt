package emulator.architecture

class Screen {
    val framebuffer = ByteArray(64)  // 8x8 display

    fun updateScreen() {
        // TODO: Implement rendering logic
    }
}

// The screen is a 8x8 ASCII display. The screen has 64 bytes of internal RAM that serves as the frame buffer for the ASCII character to display at each position.
