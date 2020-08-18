package kata.exercise.socialnetwork.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static String socialPrint(LocalDateTime date, LocalDateTime now) {
        long diff = now.toInstant(ZoneOffset.UTC).toEpochMilli() - date.toInstant(ZoneOffset.UTC).toEpochMilli();

        long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        long days = TimeUnit.MILLISECONDS.toDays(diff);

        if (minutes < 1) {
            return getTime(seconds, "second");
        } else if (hours < 1) {
            return getTime(minutes, "minute");
        } else if (days < 1) {
            return getTime(hours, "hour");
        } else {
            return getTime(days, "day");
        }
    }

    private static String getTime(long time, String unit) {
        if (time != 1) {
            return String.format("%s %ss ago", time, unit);
        } else {
            return String.format("%s %s ago", time, unit);
        }
    }
}
