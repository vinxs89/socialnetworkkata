package kata.exercise.socialnetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

public class ApplicationTest {

    private Application application;

    private Scanner scanner;
    private CommandHandler commandHandler;

    @BeforeEach
    void prepare() {
        scanner = Mockito.mock(Scanner.class);
        commandHandler = Mockito.mock(CommandHandler.class);
        application = new Application(scanner, commandHandler);
    }

    @Test
    void testCommandRedirection() {
        String input1 = "Input1";
        String input2 = "Input2";
        String input3 = "";

        Mockito.when(scanner.nextLine()).thenReturn(input1).thenReturn(input2).thenReturn(input3);

        application.run();

        Mockito.verify(scanner, Mockito.times(3)).nextLine();
        Mockito.verify(commandHandler).handleInput(input1);
        Mockito.verify(commandHandler).handleInput(input2);
        Mockito.verifyNoMoreInteractions(scanner, commandHandler);
    }
}
