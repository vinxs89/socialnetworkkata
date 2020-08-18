package kata.exercise.socialnetwork;

import kata.exercise.socialnetwork.commandhandler.DefaultCommandHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

public class ApplicationUnitTest {

    private Application application;

    private Scanner scanner;
    private DefaultCommandHandler defaultCommandHandler;

    @BeforeEach
    void prepare() {
        scanner = Mockito.mock(Scanner.class);
        defaultCommandHandler = Mockito.mock(DefaultCommandHandler.class);
        application = new Application(scanner, defaultCommandHandler);
    }

    @Test
    void testCommandRedirection() {
        String input1 = "Input1";
        String input2 = "Input2";
        String input3 = "";

        Mockito.when(scanner.nextLine()).thenReturn(input1).thenReturn(input2).thenReturn(input3);

        application.run();

        Mockito.verify(scanner, Mockito.times(3)).nextLine();
        Mockito.verify(defaultCommandHandler).handleInput(input1);
        Mockito.verify(defaultCommandHandler).handleInput(input2);
        Mockito.verifyNoMoreInteractions(scanner, defaultCommandHandler);
    }
}
