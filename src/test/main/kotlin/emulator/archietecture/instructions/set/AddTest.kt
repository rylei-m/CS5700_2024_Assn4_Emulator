import emulator.architecture.registers.RX
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalUnsignedTypes::class)
class AddTest {

    private lateinit var r: Array<RX>
    private lateinit var addInstruction: Add

    @Before
    fun setup() {
        r = arrayOf(RX(), RX(), RX())
        r[0].write(ubyteArrayOf(1u))
        r[1].write(ubyteArrayOf(2u))
        addInstruction = Add(ubyteArrayOf(0u, 1u, 2u))
        addInstruction.processNibbles()
    }

    @Test
    fun testPerformOperation() {
        addInstruction.performOperation()
        assertEquals(3u, r[2].read()[0])
    }
}
