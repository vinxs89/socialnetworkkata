package kata.exercise.socialnetwork.commandhandler;

import kata.exercise.socialnetwork.models.Message;
import kata.exercise.socialnetwork.models.User;
import kata.exercise.socialnetwork.services.MessageService;
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
    private MessageService messageService;
    private Message message;
    private User user1;
    private User user2;

    @BeforeEach
    void prepare() {
        printStream = Mockito.mock(PrintStream.class);
        userService = Mockito.mock(UserService.class);
        messageService = Mockito.mock(MessageService.class);
        message = Mockito.mock(Message.class);
        user1 = Mockito.mock(User.class);
        user2 = Mockito.mock(User.class);

        defaultCommandHandler = new DefaultCommandHandler(userService, messageService, printStream);
    }

    @Test
    void testFollowCommand() {
        Mockito.when(userService.getOrCreateUser("Alice")).thenReturn(user1);
        Mockito.when(userService.getOrCreateUser("Bob")).thenReturn(user2);

        defaultCommandHandler.handleInput("Alice follows Bob");

        Mockito.verify(userService).getOrCreateUser("Alice");
        Mockito.verify(userService).getOrCreateUser("Bob");
        Mockito.verify(user1).follow(user2);
        Mockito.verifyNoMoreInteractions(userService, messageService, printStream, user1, user2);
    }

    @Test
    void testReadCommand() {
        Mockito.when(message.formatForUser()).thenReturn("MSG");
        Mockito.when(userService.getOrCreateUser("Alice")).thenReturn(user1);
        Mockito.when(messageService.getMessages(user1)).thenReturn(Collections.singleton(message));

        defaultCommandHandler.handleInput("Alice");

        Mockito.verify(messageService).getMessages(user1);
        Mockito.verify(printStream).println("MSG");
        Mockito.verify(userService).getOrCreateUser("Alice");
        Mockito.verify(message).formatForUser();
        Mockito.verifyNoMoreInteractions(printStream, userService, messageService, user1);
    }

    @Test
    void testWallCommand() {
        Mockito.when(message.formatForWall()).thenReturn("MSG");
        Mockito.when(userService.getOrCreateUser("Alice")).thenReturn(user1);
        Mockito.when(messageService.getWall(user1)).thenReturn(Collections.singleton(message));

        defaultCommandHandler.handleInput("Alice wall");

        Mockito.verify(messageService).getWall(user1);
        Mockito.verify(printStream).println("MSG");
        Mockito.verify(userService).getOrCreateUser("Alice");
        Mockito.verify(message).formatForWall();
        Mockito.verifyNoMoreInteractions(printStream, userService, messageService, user1);
    }

    @Test
    void testPostCommand() {
        Mockito.when(userService.getOrCreateUser("Alice")).thenReturn(user1);

        defaultCommandHandler.handleInput("Alice -> This is a message");

        Mockito.verify(userService).getOrCreateUser("Alice");
        Mockito.verify(messageService).addMessage("This is a message", user1);
        Mockito.verifyNoMoreInteractions(printStream, userService, messageService, user1);
    }
}