package kata.exercise.socialnetwork.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user1;
    private User user2;

    @BeforeEach
    void prepare() {
        user1 = new User("user1");
        user2 = new User("user2");
    }

    @Test
    void testFollow() {
        assertTrue(user1.getFollowing().isEmpty());

        user1.follow(user2);

        assertEquals(1, user1.getFollowing().size());
        assertSame(user2, user1.getFollowing().iterator().next());
    }

    @Test
    void testMultipleFollow() {
        assertTrue(user1.getFollowing().isEmpty());

        user1.follow(user2);
        user1.follow(user2);
        user1.follow(user2);

        assertEquals(1, user1.getFollowing().size());
        assertSame(user2, user1.getFollowing().iterator().next());
    }

    @Test
    void testFollows() {
        assertFalse(user1.follows(user2));

        assertTrue(user1.getFollowing().isEmpty());
        user1.getFollowing().add(user2);
        assertFalse(user1.getFollowing().isEmpty());

        assertTrue(user1.follows(user2));
        assertFalse(user2.follows(user1));
    }
}