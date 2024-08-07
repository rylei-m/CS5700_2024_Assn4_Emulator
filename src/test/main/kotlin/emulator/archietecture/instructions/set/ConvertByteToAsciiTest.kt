import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalUnsignedTypes::class)
class ConvertByteToAsciiTest {

    private lateinit var r: Array<RX>
    private lateinit var convertByteToAscii: ConvertByteToAscii

    @Before
    fun setup() {
        r = arrayOf(RX(), RX())
        r[0].write(ubyteArrayOf(9u))
        convertByteToAscii = ConvertByteToAscii(ubyteArrayOf(0u, 1u))
        convertByteToAscii.processNibbles()
    }

    @Test
    fun testPerformOperation() {
        convertByteToAscii.performOperation()
        assertEquals('9'.code.toUByte(), r[1].read()[0])
    }
}
