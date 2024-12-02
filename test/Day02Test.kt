import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

class Day02Test {
    @Test
    fun `Make sure that an array of integers is safe`() {
        val input = listOf(7, 6, 4, 2, 1)
        assertTrue (isSafe(input))

        val input2 = listOf(1, 2, 7, 8, 9)
        assertFalse(isSafe(input2))

        val input3 = listOf(9, 7, 6, 2, 1)
        assertFalse(isSafe(input3))

        val input4 = listOf(1, 3, 2, 4, 5)
        assertFalse(isSafe(input4))

        val input5 = listOf(8, 6, 4, 4, 1)
        assertFalse(isSafe(input5))

        val input6 = listOf(45, 47, 50, 51, 52, 53, 55, 59)
        assertTrue(isSafe(input6))
    }
}