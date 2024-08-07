import emulator.architecture.memory.base.types.ManageRam
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalUnsignedTypes::class)
class WriteTest {

    private lateinit var r: Array<RX>
    private lateinit var writeInstruction: Write

    @Before
    fun setup() {
        r = arrayOf(RX())
        r[0].write(ubyteArrayOf(42u))
        writeInstruction = Write(ubyteArrayOf(0u))
        writeInstruction.processNibbles()
    }

    @Test
    fun testPerformOperation() {
        writeInstruction.performOperation()
        assertEquals(42u, ManageRam.ram.read(0))
    }
}
