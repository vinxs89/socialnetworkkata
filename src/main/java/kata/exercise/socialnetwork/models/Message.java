package kata.exercise.socialnetwork.models;

import kata.exercise.socialnetwork.utils.TimeUtils;

import java.time.LocalDateTime;

public class Message {

    private final String text;
    private final User user;
    private final LocalDateTime date;

    public Message(String text, User user, LocalDateTime date) {
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

    public String formatForUser() {
        return String.format("%s (%s)", text, TimeUtils.socialPrint(date, LocalDateTime.now()));
    }

    public String formatForWall() {
        return String.format("%s - %s (%s)", user.getName(), text, TimeUtils.socialPrint(date, LocalDateTime.now()));
    }
}
