package emulator.architecture

import java.util.concurrent.atomic.AtomicBoolean

class Timer {
    var value: Byte = 0
}

object PauseTimer {
    val pauseTimer = AtomicBoolean(false)
}
// Atomic Boolean: A boolean value that may be updated atomically

val timerRunnable = Runnable {
    try{
        if (PauseTimer.pauseTimer.get()) {
            return@Runnable
        }

    } catch (e: Exception) {
        print("error")
    }
}
