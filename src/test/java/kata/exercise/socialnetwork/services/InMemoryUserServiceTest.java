package kata.exercise.socialnetwork.services;

import kata.exercise.socialnetwork.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserServiceTest {

    private InMemoryUserService userService = InMemoryUserService.getInstance();

    private String username;
    private User user;

    @BeforeEach
    void clearUsers() {
        this.userService.getAllUsers().clear();

        this.username = "UID";
        this.user = Mockito.mock(User.class);
    }

    @Test
    void testGetUser() {
        Mockito.when(user.getName()).thenReturn(username);

        Map<String, User> allUsers = this.userService.getAllUsers();
        assertTrue(allUsers.isEmpty());

        allUsers.put(user.getName(), user);

        User retrieved = this.userService.getOrCreateUser(username);
        assertSame(user, retrieved);
    }

    @Test
    void testCreateUser() {
        Map<String, User> allUsers = this.userService.getAllUsers();
        assertTrue(allUsers.isEmpty());

        User retrieved = this.userService.getOrCreateUser(username);
        assertNotNull(retrieved);
        assertEquals(username, retrieved.getName());

        assertEquals(1, allUsers.size());
    }

    @Test
    void avoidDuplicationTest() {
        Map<String, User> allUsers = this.userService.getAllUsers();
        assertTrue(allUsers.isEmpty());

        User retrieved = this.userService.getOrCreateUser(username);
        assertNotNull(retrieved);
        assertEquals(username, retrieved.getName());

        User retrieved2 = this.userService.getOrCreateUser(username);
        assertNotNull(retrieved2);
        assertEquals(username, retrieved2.getName());

        assertEquals(1, allUsers.size());
    }
}