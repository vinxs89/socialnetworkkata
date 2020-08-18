package kata.exercise.socialnetwork.services;

import kata.exercise.socialnetwork.models.Message;
import kata.exercise.socialnetwork.models.User;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryDBService implements DBService {

    private static final InMemoryDBService INSTANCE = new InMemoryDBService();
    private LinkedList<Message> messages = new LinkedList<>();

    private InMemoryDBService() {
    }

    @Override
    public void addMessage(String text, User user) {
        Message message = new Message(text, user, new Date());
        messages.addFirst(message);
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
        return isUserMessage(message, user) || user.follows(message.getUser());
    }

    public static InMemoryDBService getInstance() {
        return INSTANCE;
    }
}
