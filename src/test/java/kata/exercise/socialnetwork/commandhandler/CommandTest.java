package kata.exercise.socialnetwork.commandhandler;

import kata.exercise.socialnetwork.commandhandler.Command;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CommandTest {

    @Test
    void testCommandRecognition() {
        assertEquals(Command.POST, Command.fromInput("Alice1 -> Message"));
        assertEquals(Command.POST, Command.fromInput("1Alice ->  test"));
        assertEquals(Command.READ, Command.fromInput("Alice"));
        assertEquals(Command.FOLLOW, Command.fromInput("Alice follows Bob"));
        assertEquals(Command.WALL, Command.fromInput("Alice wall"));
        assertNull(Command.fromInput("Alice test"));
        assertNull(Command.fromInput("Alice test "));
        assertNull(Command.fromInput(" Alice test"));
        assertNull(Command.fromInput("Alice Bob -> test"));
        assertNull(Command.fromInput(" Alice -> test"));
        assertNull(Command.fromInput("Alice  -> test"));
        assertNull(Command.fromInput(" Alice follows Bob"));
        assertNull(Command.fromInput("Alice follows Bob "));
        assertNull(Command.fromInput("Alice Bob wall"));
        assertNull(Command.fromInput("Alice wall "));
    }
}
