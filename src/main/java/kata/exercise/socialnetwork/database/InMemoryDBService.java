package kata.exercise.socialnetwork.database;

import kata.exercise.socialnetwork.models.Message;
import kata.exercise.socialnetwork.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryDBService implements DBService {

    public static final InMemoryDBService INSTANCE = new InMemoryDBService();
    private List<Message> messages = new ArrayList<>();

    private InMemoryDBService() {
    }

    @Override
    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public Collection<Message> getMessages(User user) {
        return messages.stream().filter(m -> isUserMessage(m, user)).collect(Collectors.toList());
    }

    @Override
    public Collection<Message> getWall(User user) {
        return messages.stream().filter(m -> isMessageInWall(m, user)).collect(Collectors.toList());
    }

    List<Message> getAllMessages() {
        return messages;
    }

    void clear() {
        messages.clear();
    }

    private boolean isUserMessage(Message message, User user) {
        return message.getUser().equals(user);
    }

    private boolean isMessageInWall(Message message, User user) {
        return isUserMessage(message, user) || user.getFollowing().contains(message.getUser());
    }
}
