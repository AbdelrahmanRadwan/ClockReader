import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClockReaderTest {

    @Test
    public void testInvalidInputs() {
        String invalidTime = "50:50";
        String invalidInput = "Invalid Input";
        assertThrows(DateTimeParseException.class, () -> {
            ClockReader.readClock(invalidTime);
        });
        assertThrows(DateTimeParseException.class, () -> {
            ClockReader.readClock(invalidInput);
        });
    }

    @Test
    public void testMidnight() {
        String time = "00:00";
        String expected = "It's midnight";
        String result = ClockReader.readClock(time);
        assertEquals(expected, result);
    }

    @Test
    public void testMidnightAndMinutes() {
        String time = "00:54";
        String expected = "It's midnight fifty four";
        String result = ClockReader.readClock(time);
        assertEquals(expected, result);
    }

    @Test
    public void testMidday() {
        String time = "12:00";
        String expected = "It's midday";
        String result = ClockReader.readClock(time);
        assertEquals(expected, result);
    }

    @Test
    public void testOnTheHour() {
        String time = "08:00";
        String expected = "It's eight";
        String result = ClockReader.readClock(time);
        assertEquals(expected, result);
    }

    @Test
    public void testPastTheHour() {
        String time = "08:34";
        String expected = "It's eight thirty four";
        String result = ClockReader.readClock(time);
        assertEquals(expected, result);
    }

    @Test
    public void testMinutesLargerThanTwenty() {
        String time = "08:55";
        String expected = "It's eight fifty five";
        String result = ClockReader.readClock(time);
        assertEquals(expected, result);
    }

    @Test
    public void testHalfPast() {
        String time = "08:30";
        String expected = "It's eight thirty";
        String result = ClockReader.readClock(time);
        assertEquals(expected, result);
    }

    @Test
    public void testTwentyPast() {
        String time = "08:20";
        String expected = "It's eight twenty";
        String result = ClockReader.readClock(time);
        assertEquals(expected, result);
    }

    @Test
    public void testTwentyTo() {
        String time = "08:40";
        String expected = "It's eight forty";
        String result = ClockReader.readClock(time);
        assertEquals(expected, result);
    }
}
