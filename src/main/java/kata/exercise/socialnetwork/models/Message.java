package kata.exercise.socialnetwork.models;

import java.util.Date;

public class Message {

    private final String text;
    private final User user;
    private final Date date;

    public Message(String text, User user, Date date) {
        this.text = text;
        this.user = user;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }
}
