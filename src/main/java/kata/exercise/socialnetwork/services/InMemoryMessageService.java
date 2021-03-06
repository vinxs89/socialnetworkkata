package kata.exercise.socialnetwork.services;

import kata.exercise.socialnetwork.models.Message;
import kata.exercise.socialnetwork.models.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryMessageService implements MessageService {

    private static final InMemoryMessageService INSTANCE = new InMemoryMessageService();
    private LinkedList<Message> messages = new LinkedList<>();

    private InMemoryMessageService() {
    }

    @Override
    public void addMessage(String text, User user) {
        Message message = new Message(text, user, LocalDateTime.now());
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

    private boolean isUserMessage(Message message, User user) {
        return message.getUser().equals(user);
    }

    private boolean isMessageInWall(Message message, User user) {
        return isUserMessage(message, user) || user.follows(message.getUser());
    }

    public static InMemoryMessageService getInstance() {
        return INSTANCE;
    }
}
