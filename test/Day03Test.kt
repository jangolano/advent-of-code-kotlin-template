import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day03Test {

    @Test
    fun `Given a string I can find the correct commands`(){
        val inputString = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
        val commands = findAcceptableCommands(inputString)
        assertEquals("mul(2,4)", commands[0])
        assertEquals("mul(5,5)", commands[1])
        assertEquals("mul(11,8)", commands[2])
        assertEquals("mul(8,5)", commands[3])
    }
}