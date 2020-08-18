package kata.exercise.socialnetwork.models;

import java.util.HashSet;
import java.util.Set;

public class User {

    private final String name;
    private final Set<User> following;

    public User(String name) {
        this.name = name;
        this.following = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    Set<User> getFollowing() {
        return following;
    }

    public void follow(User anotherUser) {
        following.add(anotherUser);
    }

    public boolean follows(User user) {
        return following.contains(user);
    }
}
