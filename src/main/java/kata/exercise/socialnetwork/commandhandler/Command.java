package kata.exercise.socialnetwork.commandhandler;

import java.util.regex.Pattern;

public enum Command {
    POST(Pattern.compile("[0-z]+ -> .+")),
    READ(Pattern.compile("[0-z]+")),
    FOLLOW(Pattern.compile("[0-z]+ follows [0-z]+")),
    WALL(Pattern.compile("[0-z]+ wall"));

    private Pattern pattern;

    Command(Pattern pattern) {
        this.pattern = pattern;
    }

    public static Command fromInput(String input) {
        for (Command command : Command.values()) {
            if (command.pattern.matcher(input).matches()) {
                return command;
            }
        }
        return null;
    }
}
