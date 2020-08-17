package kata.exercise.socialnetwork.database;

import kata.exercise.socialnetwork.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserServiceTest {

    private InMemoryUserService userService = InMemoryUserService.INSTANCE;

    @BeforeEach
    void clearUsers() {
        this.userService.clear();
    }

    @Test
    void testGetUser() {
        String username = "User1";
        User user = Mockito.mock(User.class);

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

        User retrieved = this.userService.getOrCreateUser("User1");
        assertNotNull(retrieved);
        assertEquals("User1", retrieved.getName());
    }
}