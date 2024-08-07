import emulator.architecture.memory.base.types.ManageRam
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalUnsignedTypes::class)
class ReadTest {

    private lateinit var r: Array<RX>
    private lateinit var readInstruction: Read

    @Before
    fun setup() {
        r = arrayOf(RX())
        ManageRam.ram.write(0, 42u)
        readInstruction = Read(ubyteArrayOf(0u))
        readInstruction.processNibbles()
    }

    @Test
    fun testPerformOperation() {
        readInstruction.performOperation()
        assertEquals(42u, r[0].read()[0])
    }
}
