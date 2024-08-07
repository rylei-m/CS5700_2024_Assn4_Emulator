import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalUnsignedTypes::class)
class JumpTest {

    private lateinit var jumpInstruction: Jump

    @Before
    fun setup() {
        jumpInstruction = Jump(ubyteArrayOf(1u, 2u, 3u))
        jumpInstruction.processNibbles()
    }

    @Test
    fun testProcessNibbles() {
        assertEquals(0x123, Utili().byteArrayToInt(jumpInstruction.addressBytes))
    }
}
