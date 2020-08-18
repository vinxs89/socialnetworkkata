package kata.exercise.socialnetwork.services;

import kata.exercise.socialnetwork.models.Message;
import kata.exercise.socialnetwork.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryMessageServiceTest {

    private InMemoryMessageService messageService = InMemoryMessageService.getInstance();

    private User user1;
    private User user2;
    private Message message1;
    private Message message2;
    private String text1;
    private String text2;

    @BeforeEach
    void init() {
        this.messageService.getAllMessages().clear();

        user1 = Mockito.mock(User.class);
        user2 = Mockito.mock(User.class);
        message1 = Mockito.mock(Message.class);
        message2 = Mockito.mock(Message.class);
        text1 = "TXT1";
        text2 = "TXT2";
    }

    @Test
    void addMessage() {
        List<Message> allMessages = this.messageService.getAllMessages();
        assertTrue(allMessages.isEmpty());

        this.messageService.addMessage(text1, user1);

        allMessages = this.messageService.getAllMessages();
        assertEquals(1, allMessages.size());

        Message retrievedMessage = allMessages.get(0);
        assertSame(text1, retrievedMessage.getText());
        assertSame(user1, retrievedMessage.getUser());
    }

    @Test
    void testReverseOrderAddMessage() {
        List<Message> allMessages = this.messageService.getAllMessages();
        assertTrue(allMessages.isEmpty());

        this.messageService.addMessage(text1, user1);
        this.messageService.addMessage(text2, user1);

        allMessages = this.messageService.getAllMessages();
        assertEquals(2, allMessages.size());

        Message firstMessage = allMessages.get(0);
        assertSame(text2, firstMessage.getText());

        Message secondMessage = allMessages.get(1);
        assertSame(text1, secondMessage.getText());
    }

    @Test
    void testGetExistingMessages() {
        Mockito.when(message1.getUser()).thenReturn(user1);
        Mockito.when(message2.getUser()).thenReturn(user2);

        List<Message> allMessages = this.messageService.getAllMessages();
        allMessages.add(message1);
        allMessages.add(message2);

        Collection<Message> messagesUser1 = this.messageService.getMessages(user1);
        assertEquals(1, messagesUser1.size());
        assertSame(message1, messagesUser1.iterator().next());

        Collection<Message> messagesUser2 = this.messageService.getMessages(user2);
        assertEquals(1, messagesUser2.size());
        assertSame(message2, messagesUser2.iterator().next());
    }

    @Test
    void testNonExistingMessages() {
        Mockito.when(message1.getUser()).thenReturn(user1);

        List<Message> allMessages = this.messageService.getAllMessages();
        allMessages.add(message1);

        Collection<Message> messagesUser2 = this.messageService.getMessages(user2);
        assertTrue(messagesUser2.isEmpty());
    }

    @Test
    void testWallBeforeFollowing() {
        Mockito.when(user1.follows(user2)).thenReturn(false);
        Mockito.when(message1.getUser()).thenReturn(user1);
        Mockito.when(message2.getUser()).thenReturn(user2);

        List<Message> allMessages = this.messageService.getAllMessages();
        allMessages.add(message1);
        allMessages.add(message2);

        Collection<Message> wallUser1 = this.messageService.getWall(user1);
        assertEquals(1, wallUser1.size());
        assertSame(message1, wallUser1.iterator().next());

        Collection<Message> wallUser2 = this.messageService.getWall(user2);
        assertEquals(1, wallUser2.size());
        assertSame(message2, wallUser2.iterator().next());
    }

    @Test
    void testWallAfterFollowing() {
        Mockito.when(user1.follows(user2)).thenReturn(true);
        Mockito.when(message1.getUser()).thenReturn(user1);
        Mockito.when(message2.getUser()).thenReturn(user2);

        List<Message> allMessages = this.messageService.getAllMessages();
        allMessages.add(message1);
        allMessages.add(message2);

        Collection<Message> wallUser1 = this.messageService.getWall(user1);
        assertEquals(2, wallUser1.size());
        assertSame(message1, wallUser1.iterator().next());
        assertTrue(wallUser1.contains(message2));

        Collection<Message> wallUser2 = this.messageService.getWall(user2);
        assertEquals(1, wallUser2.size());
        assertSame(message2, wallUser2.iterator().next());
    }

}