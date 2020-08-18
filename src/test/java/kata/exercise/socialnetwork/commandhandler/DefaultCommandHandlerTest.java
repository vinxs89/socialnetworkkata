package kata.exercise.socialnetwork.commandhandler;

import kata.exercise.socialnetwork.models.Message;
import kata.exercise.socialnetwork.models.User;
import kata.exercise.socialnetwork.services.DBService;
import kata.exercise.socialnetwork.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.util.Collections;

class DefaultCommandHandlerTest {

    private DefaultCommandHandler defaultCommandHandler;

    private PrintStream printStream;
    private UserService userService;
    private DBService dbService;
    private Message message;
    private User user1;
    private User user2;

    @BeforeEach
    void prepare() {
        printStream = Mockito.mock(PrintStream.class);
        userService = Mockito.mock(UserService.class);
        dbService = Mockito.mock(DBService.class);
        message = Mockito.mock(Message.class);
        user1 = Mockito.mock(User.class);
        user2 = Mockito.mock(User.class);

        defaultCommandHandler = new DefaultCommandHandler(userService, dbService, printStream);
    }

    @Test
    void testFollowCommand() {
        Mockito.when(userService.getOrCreateUser("Alice")).thenReturn(user1);
        Mockito.when(userService.getOrCreateUser("Bob")).thenReturn(user2);

        defaultCommandHandler.handleInput("Alice follows Bob");

        Mockito.verify(userService).getOrCreateUser("Alice");
        Mockito.verify(userService).getOrCreateUser("Bob");
        Mockito.verify(user1).follow(user2);
        Mockito.verifyNoMoreInteractions(userService, dbService, printStream, user1, user2);
    }

    @Test
    void testReadCommand() {
        Mockito.when(message.getTextString()).thenReturn("MSG");
        Mockito.when(userService.getOrCreateUser("Alice")).thenReturn(user1);
        Mockito.when(dbService.getMessages(user1)).thenReturn(Collections.singleton(message));

        defaultCommandHandler.handleInput("Alice");

        Mockito.verify(dbService).getMessages(user1);
        Mockito.verify(printStream).println("MSG");
        Mockito.verify(userService).getOrCreateUser("Alice");
        Mockito.verify(message).getTextString();
        Mockito.verifyNoMoreInteractions(printStream, userService, dbService, user1);
    }

    @Test
    void testWallCommand() {
        Mockito.when(message.getMessageString()).thenReturn("MSG");
        Mockito.when(userService.getOrCreateUser("Alice")).thenReturn(user1);
        Mockito.when(dbService.getWall(user1)).thenReturn(Collections.singleton(message));

        defaultCommandHandler.handleInput("Alice wall");

        Mockito.verify(dbService).getWall(user1);
        Mockito.verify(printStream).println("MSG");
        Mockito.verify(userService).getOrCreateUser("Alice");
        Mockito.verify(message).getMessageString();
        Mockito.verifyNoMoreInteractions(printStream, userService, dbService, user1);
    }

    @Test
    void testPostCommand() {
        Mockito.when(userService.getOrCreateUser("Alice")).thenReturn(user1);

        defaultCommandHandler.handleInput("Alice -> This is a message");

        Mockito.verify(userService).getOrCreateUser("Alice");
        Mockito.verify(dbService).addMessage("This is a message", user1);
        Mockito.verifyNoMoreInteractions(printStream, userService, dbService, user1);
    }
}