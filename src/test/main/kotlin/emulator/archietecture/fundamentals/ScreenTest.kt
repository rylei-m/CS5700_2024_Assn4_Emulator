import org.junit.Test
import kotlin.test.assertFailsWith

@OptIn(ExperimentalUnsignedTypes::class)
class ScreenTest {

    @Test
    fun testDrawWithinBounds() {
        val screen = Screen()
        screen.draw(0xFFu, 0u, 0u)
        screen.draw(0xFFu, 7u, 7u)
    }

    @Test
    fun testDrawOutOfBounds() {
        val screen = Screen()
        assertFailsWith<IllegalArgumentException> { screen.draw(0xFFu, 8u, 0u) }
        assertFailsWith<IllegalArgumentException> { screen.draw(0xFFu, 0u, 8u) }
    }
}
