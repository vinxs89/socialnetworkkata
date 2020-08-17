package kata.exercise.socialnetwork.database;

import kata.exercise.socialnetwork.models.Message;
import kata.exercise.socialnetwork.models.User;

public interface DBService {

    void addMessage(Message message);

    Iterable<Message> getMessages(User user);

    Iterable<Message> getWall(User user);

}
