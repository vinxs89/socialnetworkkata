package kata.exercise.socialnetwork.database;

import kata.exercise.socialnetwork.models.Message;
import kata.exercise.socialnetwork.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryDBServiceTest {

    private InMemoryDBService dbService = InMemoryDBService.INSTANCE;

    @BeforeEach
    void clearMessages() {
        this.dbService.clear();
    }

    @Test
    void addMessage() {
        Message message = Mockito.mock(Message.class);

        List<Message> allMessages = this.dbService.getAllMessages();
        assertTrue(allMessages.isEmpty());

        this.dbService.addMessage(message);

        allMessages = this.dbService.getAllMessages();
        assertEquals(1, allMessages.size());

        Message retrievedMessage = allMessages.get(0);
        assertSame(message, retrievedMessage);
    }

    @Test
    void testGetExistingMessages() {
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        Message message1 = Mockito.mock(Message.class);
        Message message2 = Mockito.mock(Message.class);

        Mockito.when(message1.getUser()).thenReturn(user1);
        Mockito.when(message2.getUser()).thenReturn(user2);

        List<Message> allMessages = this.dbService.getAllMessages();
        allMessages.add(message1);
        allMessages.add(message2);

        Collection<Message> messagesUser1 = this.dbService.getMessages(user1);
        assertEquals(1, messagesUser1.size());
        assertSame(message1, messagesUser1.iterator().next());

        Collection<Message> messagesUser2 = this.dbService.getMessages(user2);
        assertEquals(1, messagesUser2.size());
        assertSame(message2, messagesUser2.iterator().next());
    }

    @Test
    void testNonExistingMessages() {
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        User user1Bis = Mockito.mock(User.class);
        Message message1 = Mockito.mock(Message.class);

        Mockito.when(message1.getUser()).thenReturn(user1);

        List<Message> allMessages = this.dbService.getAllMessages();
        allMessages.add(message1);

        Collection<Message> messagesUser2 = this.dbService.getMessages(user2);
        assertTrue(messagesUser2.isEmpty());

        Collection<Message> messagesUser1Bis = this.dbService.getMessages(user1Bis);
        assertTrue(messagesUser1Bis.isEmpty());
    }

    @Test
    void testWallBeforeFollowing() {
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        Message message1 = Mockito.mock(Message.class);
        Message message2 = Mockito.mock(Message.class);

        Mockito.when(message1.getUser()).thenReturn(user1);
        Mockito.when(message2.getUser()).thenReturn(user2);

        List<Message> allMessages = this.dbService.getAllMessages();
        allMessages.add(message1);
        allMessages.add(message2);

        Collection<Message> wallUser1 = this.dbService.getWall(user1);
        assertEquals(1, wallUser1.size());
        assertSame(message1, wallUser1.iterator().next());

        Collection<Message> wallUser2 = this.dbService.getWall(user2);
        assertEquals(1, wallUser2.size());
        assertSame(message2, wallUser2.iterator().next());
    }

    @Test
    void testWallAfterFollowing() {
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        Message message1 = Mockito.mock(Message.class);
        Message message2 = Mockito.mock(Message.class);

        Mockito.when(user1.getFollowing()).thenReturn(Collections.singleton(user2));
        Mockito.when(message1.getUser()).thenReturn(user1);
        Mockito.when(message2.getUser()).thenReturn(user2);

        List<Message> allMessages = this.dbService.getAllMessages();
        allMessages.add(message1);
        allMessages.add(message2);

        Collection<Message> wallUser1 = this.dbService.getWall(user1);
        assertEquals(2, wallUser1.size());
        assertSame(message1, wallUser1.iterator().next());
        assertTrue(wallUser1.contains(message2));

        Collection<Message> wallUser2 = this.dbService.getWall(user2);
        assertEquals(1, wallUser2.size());
        assertSame(message2, wallUser2.iterator().next());
    }

}