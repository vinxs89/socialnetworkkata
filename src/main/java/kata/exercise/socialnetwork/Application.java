package kata.exercise.socialnetwork;

import kata.exercise.socialnetwork.services.InMemoryDBService;
import kata.exercise.socialnetwork.services.InMemoryUserService;

import java.util.Scanner;

public class Application {

    private Scanner scanner;
    private CommandHandler commandHandler;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandHandler commandHandler = new CommandHandler(InMemoryUserService.getInstance(), InMemoryDBService.getInstance(), System.out);

        new Application(scanner, commandHandler).run();
    }

    public Application(Scanner scanner, CommandHandler commandHandler) {
        this.scanner = scanner;
        this.commandHandler = commandHandler;
    }

    public void run() {
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            commandHandler.handleInput(line);
        }
    }
}
