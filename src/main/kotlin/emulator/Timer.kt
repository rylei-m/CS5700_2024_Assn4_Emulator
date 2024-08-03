package emulator

import java.util.concurrent.atomic.AtomicBoolean

class Timer {
    var value: Byte = 0
}

object pauseTimer {
    val pauseTimer = AtomicBoolean(false)
}
// Atomic Boolean: A boolean value that may be updated atomically
