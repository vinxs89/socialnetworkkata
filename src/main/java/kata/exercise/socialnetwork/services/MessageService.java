package kata.exercise.socialnetwork.services;

import kata.exercise.socialnetwork.models.Message;
import kata.exercise.socialnetwork.models.User;

import java.util.Collection;

public interface MessageService {

    void addMessage(String message, User user);

    Collection<Message> getMessages(User user);

    Collection<Message> getWall(User user);

}
