import org.junit.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalUnsignedTypes::class)
class InstructionFactoryTest {

    @Test
    fun testCreateInstruction() {
        val factory = InstructionFactory()
        val instruction = factory.createInstruction(0u, 0u, 0u, 0u)
        assertTrue(instruction is Store)
    }
}
