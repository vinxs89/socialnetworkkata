package kata.exercise.socialnetwork.models;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    private String formatDate(Date date) {
        Date now = new Date();
        long diff = now.getTime() - date.getTime();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
        if (seconds > 60) {
            long minutes = TimeUnit.SECONDS.toMinutes(seconds);
            if (minutes == 1) {
                return "1 minute ago";
            } else {
                return minutes + " minutes ago";
            }
        } else if (seconds == 1) {
            return "1 second ago";
        } else {
            return seconds + " seconds ago";
        }
    }

    public String getTextString() {
        return String.format("%s (%s)", text, formatDate(date));
    }

    public String getMessageString() {
        return String.format("%s: %s (%s)", user.getName(), text, formatDate(date));
    }
}
