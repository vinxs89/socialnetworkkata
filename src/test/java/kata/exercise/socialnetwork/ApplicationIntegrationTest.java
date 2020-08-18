package kata.exercise.socialnetwork;

import kata.exercise.socialnetwork.commandhandler.DefaultCommandHandler;
import kata.exercise.socialnetwork.services.DBService;
import kata.exercise.socialnetwork.services.InMemoryDBService;
import kata.exercise.socialnetwork.services.InMemoryUserService;
import kata.exercise.socialnetwork.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class ApplicationIntegrationTest {

    private Application application;
    private Scanner scanner;
    private StringListPrintStream printStream;

    @BeforeEach
    void prepare() {
        scanner = Mockito.mock(Scanner.class);
        printStream = new StringListPrintStream();
        UserService userService = InMemoryUserService.getInstance();
        DBService dbService = InMemoryDBService.getInstance();
        DefaultCommandHandler defaultCommandHandler = new DefaultCommandHandler(userService, dbService, printStream);
        application = new Application(scanner, defaultCommandHandler);
    }

    @Test
    void integrationTest() {
        String[] inputs = new String[]{
                "Alice -> alice first message",
                "Alice",
                "Bob -> Bob message",
                "Bob",
                "Alice wall",
                "Alice -> alice second message",
                "Alice follows Bob",
                "Alice wall",
                "Bob wall",
                "Charlie -> Charlie message",
                "Charlie follows Alice",
                "Charlie follows Bob",
                "Charlie wall",
                ""
        };
        Mockito.when(scanner.nextLine()).thenReturn("Alice", inputs);

        LocalDateTime date = LocalDateTime.of(2020, 8, 5, 11, 15, 0);
        try (final MockedStatic<LocalDateTime> mockedTime = Mockito.mockStatic(LocalDateTime.class)) {
            mockedTime.when(LocalDateTime::now).thenReturn(date);
            application.run();
        }

        List<String> expectedOutput = Arrays.asList(
                "alice first message (0 seconds ago)",
                "Bob message (0 seconds ago)",
                "Alice - alice first message (0 seconds ago)",
                "Alice - alice second message (0 seconds ago)",
                "Bob - Bob message (0 seconds ago)",
                "Alice - alice first message (0 seconds ago)",
                "Bob - Bob message (0 seconds ago)",
                "Charlie - Charlie message (0 seconds ago)",
                "Alice - alice second message (0 seconds ago)",
                "Bob - Bob message (0 seconds ago)",
                "Alice - alice first message (0 seconds ago)"
        );

        assertIterableEquals(expectedOutput, printStream.messages);
    }

    private static class StringListPrintStream extends PrintStream {
        private List<String> messages = new ArrayList<>();

        StringListPrintStream() {
            super(new ByteArrayOutputStream());
        }

        @Override
        public void println(String x) {
            messages.add(x);
        }
    }

    ;
}
