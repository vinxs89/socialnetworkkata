package kata.exercise.socialnetwork.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeUtilsTest {

    private LocalDateTime now = LocalDateTime.of(2020, 8, 18, 10, 20, 5);

    @Test
    void testZeroSecondsString() {
        LocalDateTime date = LocalDateTime.of(2020, 8, 18, 10, 20, 5);
        assertEquals("0 seconds ago", TimeUtils.socialPrint(date, now));
    }

    @Test
    void testSingleSecondString() {
        LocalDateTime date = LocalDateTime.of(2020, 8, 18, 10, 20, 4);
        assertEquals("1 second ago", TimeUtils.socialPrint(date, now));
    }

    @Test
    void testMultipleSecondsString() {
        LocalDateTime date = LocalDateTime.of(2020, 8, 18, 10, 20, 2);
        assertEquals("3 seconds ago", TimeUtils.socialPrint(date, now));
    }

    @Test
    void testMaxSecondsString() {
        LocalDateTime date = LocalDateTime.of(2020, 8, 18, 10, 19, 6);
        assertEquals("59 seconds ago", TimeUtils.socialPrint(date, now));
    }

    @Test
    void testSingleMinuteString() {
        LocalDateTime date = LocalDateTime.of(2020, 8, 18, 10, 19, 5);
        assertEquals("1 minute ago", TimeUtils.socialPrint(date, now));
    }

    @Test
    void testMultipleMinutesString() {
        LocalDateTime date = LocalDateTime.of(2020, 8, 18, 10, 18, 5);
        assertEquals("2 minutes ago", TimeUtils.socialPrint(date, now));
    }

    @Test
    void testSingleHourString() {
        LocalDateTime date = LocalDateTime.of(2020, 8, 18, 9, 18, 5);
        assertEquals("1 hour ago", TimeUtils.socialPrint(date, now));
    }

    @Test
    void testMultipleHoursString() {
        LocalDateTime date = LocalDateTime.of(2020, 8, 18, 7, 18, 5);
        assertEquals("3 hours ago", TimeUtils.socialPrint(date, now));
    }

    @Test
    void testSingleDayString() {
        LocalDateTime date = LocalDateTime.of(2020, 8, 17, 10, 18, 5);
        assertEquals("1 day ago", TimeUtils.socialPrint(date, now));
    }

    @Test
    void testMultipleDaysString() {
        LocalDateTime date = LocalDateTime.of(2020, 8, 16, 10, 18, 5);
        assertEquals("2 days ago", TimeUtils.socialPrint(date, now));
    }

    @Test
    void testALotOfDaysString() {
        LocalDateTime date = LocalDateTime.of(2020, 1, 16, 10, 18, 5);
        assertEquals("215 days ago", TimeUtils.socialPrint(date, now));
    }
}