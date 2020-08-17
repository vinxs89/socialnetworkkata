package kata.exercise.socialnetwork.strategies;

import kata.exercise.socialnetwork.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandStrategyContextTest {

    @Test
    void testContext() {
        assertNotNull(CommandStrategyContext.INSTANCE);
        assertTrue(CommandStrategyContext.INSTANCE.getStrategy(Command.POST) instanceof PostCommandStrategy);
        assertTrue(CommandStrategyContext.INSTANCE.getStrategy(Command.READ) instanceof ReadCommandStrategy);
        assertTrue(CommandStrategyContext.INSTANCE.getStrategy(Command.FOLLOW) instanceof FollowCommandStrategy);
        assertTrue(CommandStrategyContext.INSTANCE.getStrategy(Command.WALL) instanceof WallCommandStrategy);
    }
}
