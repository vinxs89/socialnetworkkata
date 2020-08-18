package kata.exercise.socialnetwork;

import kata.exercise.socialnetwork.commandhandler.CommandHandler;
import kata.exercise.socialnetwork.commandhandler.DefaultCommandHandler;
import kata.exercise.socialnetwork.services.InMemoryDBService;
import kata.exercise.socialnetwork.services.InMemoryUserService;

import java.util.Scanner;

public class Application {

    private Scanner scanner;
    private CommandHandler commandHandler;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandHandler commandHandler = new DefaultCommandHandler(InMemoryUserService.getInstance(), InMemoryDBService.getInstance(), System.out);
        new Application(scanner, commandHandler).run();
    }

    Application(Scanner scanner, CommandHandler commandHandler) {
        this.scanner = scanner;
        this.commandHandler = commandHandler;
    }

    void run() {
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            commandHandler.handleInput(line);
        }
    }
}
