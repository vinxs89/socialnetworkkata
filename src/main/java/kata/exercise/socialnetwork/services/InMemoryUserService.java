package kata.exercise.socialnetwork.services;

import kata.exercise.socialnetwork.models.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserService implements UserService {

    private static final InMemoryUserService INSTANCE = new InMemoryUserService();
    private Map<String, User> userMap = new HashMap<>();

    private InMemoryUserService() {
    }

    @Override
    public User getOrCreateUser(String id) {
        if (this.userMap.containsKey(id)) {
            return this.userMap.get(id);
        } else {
            User user = new User(id);
            this.userMap.put(id, user);
            return user;
        }
    }

    public static InMemoryUserService getInstance() {
        return INSTANCE;
    }

    Map<String, User> getAllUsers() {
        return this.userMap;
    }

    void clear() {
        this.userMap.clear();
    }
}
