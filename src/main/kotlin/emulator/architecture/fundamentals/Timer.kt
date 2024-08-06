package emulator.architecture.fundamentals

import emulator.Facade.ManageT.t
import java.util.concurrent.atomic.AtomicBoolean

class Timer {
    var value: UByte = 0u
}

object PauseTimer {
    val pauseTimer = AtomicBoolean(false)
}
// Atomic Boolean: A boolean value that may be updated atomically

@OptIn(ExperimentalUnsignedTypes::class)
val timerRunnable = Runnable {
    try {
        if (PauseTimer.pauseTimer.get()) {
            return@Runnable
        }

        val currentTime = t.read()[0].toUByte().toInt()
        if (currentTime > 0) {
            t.write(ubyteArrayOf((currentTime -1).toUByte()))
        }
        
    } catch (e: Exception) {
        print("error")
    }
}
