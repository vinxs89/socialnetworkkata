package kata.exercise.socialnetwork.models;

import kata.exercise.socialnetwork.utils.TimeUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class MessageTest {


    @Test
    void testMultipleSecondsString() {
        LocalDateTime date = LocalDateTime.of(2020, 8, 18, 10, 20, 0);
        LocalDateTime now = LocalDateTime.of(2020, 8, 18, 10, 20, 0);

        try (
                final MockedStatic<TimeUtils> mockTimeUtils = Mockito.mockStatic(TimeUtils.class);
                final MockedStatic<LocalDateTime> mockDateTime = Mockito.mockStatic(LocalDateTime.class)
        ) {
            mockTimeUtils.when(() -> TimeUtils.socialPrint(date, now)).thenReturn("result");
            mockDateTime.when(LocalDateTime::now).thenReturn(now);
            Message message = new Message("text", new User("user"), date);

            assertEquals("text (result)", message.getTextString());
            assertEquals("user - text (result)", message.getMessageString());

            mockTimeUtils.verify(times(2), () -> TimeUtils.socialPrint(date, now));
            mockDateTime.verify(times(2), LocalDateTime::now);
        }
    }

}