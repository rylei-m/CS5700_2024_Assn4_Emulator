import emulator.architecture.CPU
import emulator.architecture.memory.base.types.Rom
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.test.assertFailsWith

class ExecutorTest {

    @Test
    fun testExecuteProgram() {
        val rom = Rom(UByteArray(4096))
        val executor = Executor()

        executor.executeProgram(rom)

        // Here we should verify the behavior of the executor, but without mocking dependencies like CPU, it's complex.
        // For demonstration, we just verify that it doesn't throw any exceptions.
    }

    @Test
    fun testShutdown() {
        val executor = Executor()
        executor.shutdown()
        assertFailsWith<Exception> { executor.executor.schedule({ println("test") }, 0, TimeUnit.MILLISECONDS) }
    }
}
