package emulator.architecture.fundamentals

import emulator.architecture.registers.ManageT.t
import java.util.concurrent.atomic.AtomicBoolean

class Timer {
    var value: Byte = 0
}

object PauseTimer {
    val pauseTimer = AtomicBoolean(false)
}
// Atomic Boolean: A boolean value that may be updated atomically

val timerRunnable = Runnable {
    try {
        if (PauseTimer.pauseTimer.get()) {
            return@Runnable
        }

        val currentTime = t.read()[0].toInt()
        if (currentTime > 0) {
            t.write(byteArrayOf((currentTime -1).toByte()))
        }
        
    } catch (e: Exception) {
        print("error")
    }
}
