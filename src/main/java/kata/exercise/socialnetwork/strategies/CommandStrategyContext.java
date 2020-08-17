package kata.exercise.socialnetwork.strategies;

import kata.exercise.socialnetwork.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandStrategyContext {

    public static CommandStrategyContext INSTANCE = new CommandStrategyContext();

    private Map<Command, CommandStrategy> ctx = new HashMap<>();

    private CommandStrategyContext() {
        ctx.put(Command.POST, new PostCommandStrategy());
        ctx.put(Command.READ, new ReadCommandStrategy());
        ctx.put(Command.FOLLOW, new FollowCommandStrategy());
        ctx.put(Command.WALL, new WallCommandStrategy());
    }

    public CommandStrategy getStrategy(Command command) {
        return ctx.get(command);
    }
}
