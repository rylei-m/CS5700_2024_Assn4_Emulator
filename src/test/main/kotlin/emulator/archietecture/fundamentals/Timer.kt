import org.junit.Test
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.test.assertEquals

@OptIn(ExperimentalUnsignedTypes::class)
class TimerTest {

    @Test
    fun testPauseTimer() {
        PauseTimer.pauseTimer.set(true)
        assertEquals(true, PauseTimer.pauseTimer.get())

        PauseTimer.pauseTimer.set(false)
        assertEquals(false, PauseTimer.pauseTimer.get())
    }
}
