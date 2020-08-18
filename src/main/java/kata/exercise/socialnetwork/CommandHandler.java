package kata.exercise.socialnetwork;

import kata.exercise.socialnetwork.models.Message;
import kata.exercise.socialnetwork.models.User;
import kata.exercise.socialnetwork.services.DBService;
import kata.exercise.socialnetwork.services.UserService;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CommandHandler implements InputHandler {

    private UserService userService;
    private DBService dbService;
    private PrintStream printStream;

    CommandHandler(UserService userService, DBService dbService, PrintStream printStream) {
        this.userService = userService;
        this.dbService = dbService;
        this.printStream = printStream;
    }

    @Override
    public void handleInput(String input) {
        Command command = Command.fromInput(input);
        String[] splitInput = input.split(" ");
        User user = userService.getOrCreateUser(splitInput[0]);

        switch (command) {
            case POST:
                List<String> toList = Arrays.asList(splitInput);
                String message = String.join(" ", toList.subList(2, toList.size()));
                dbService.addMessage(message, user);
                break;
            case READ:
                Collection<Message> userMessages = this.dbService.getMessages(user);
                userMessages.forEach(m -> this.printStream.println(m.getTextString()));
                break;
            case FOLLOW:
                User user2 = this.userService.getOrCreateUser(splitInput[2]);
                user.follow(user2);
                break;
            case WALL:
                Collection<Message> userWall = this.dbService.getWall(user);
                userWall.forEach(m -> this.printStream.println(m.getMessageString()));
                break;
        }
    }
}