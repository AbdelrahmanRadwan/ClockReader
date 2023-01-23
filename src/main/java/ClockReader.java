import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ClockReader {
    private static final int NUMBER_TWENTY = 20;
    private static final int HOUR_TWELVE = 12;
    private static final int NUMBER_TEN = 10;

    private static final String MIDNIGHT = "midnight";
    private static final String MIDDAY = "midday";
    private static final String MESSAGE_PREFIX = "It's ";

    private static final List<String> NUMBER_TO_WORDS_ONES = ImmutableList.of("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen");
    private static final List<String> NUMBER_TO_WORDS_TENS = ImmutableList.of("", "", "twenty", "thirty", "forty", "fifty");


    public static String readClock(final String time) {
        StringBuilder clockStringBuilder = new StringBuilder();
        clockStringBuilder.append(MESSAGE_PREFIX);
        try {
            final LocalTime localTime = LocalTime.parse(time);
            final String hours = readHours(localTime.getHour());
            final String minutes = readMinutes(localTime.getMinute());
            clockStringBuilder.append(hours);
            if (minutes != null) {
                clockStringBuilder.append(minutes);
            }
            return clockStringBuilder.toString();
        } catch (final DateTimeParseException DateTimeParseException) {
            log.warn("Warning: Input should follow format HH:MM");
            throw DateTimeParseException;
        }
    }

    public static String readHours(final int hours) {
        if (hours == 0) {
            return MIDNIGHT;
        } else if (hours == HOUR_TWELVE) {
            return MIDDAY;
        } else if (hours % NUMBER_TEN == 0) {
            return NUMBER_TO_WORDS_TENS.get(hours / NUMBER_TEN);
        } else if (hours < NUMBER_TWENTY) {
            return NUMBER_TO_WORDS_ONES.get(hours);
        } else {
            return String.format("%s %s", NUMBER_TO_WORDS_TENS.get(hours / NUMBER_TEN), NUMBER_TO_WORDS_ONES.get(hours % NUMBER_TEN));
        }
    }

    public static String readMinutes(final int minutes) {
        final String EMPTY_SPACE = " ";
        if (minutes == 0) {
            return null;
        } else if (minutes < NUMBER_TWENTY) {
            return EMPTY_SPACE + NUMBER_TO_WORDS_ONES.get(minutes);
        } else if (minutes % NUMBER_TEN == 0) {
            return EMPTY_SPACE + NUMBER_TO_WORDS_TENS.get(minutes / NUMBER_TEN);
        } else {
            return EMPTY_SPACE + String.format("%s %s", NUMBER_TO_WORDS_TENS.get(minutes / NUMBER_TEN), NUMBER_TO_WORDS_ONES.get(minutes % NUMBER_TEN));
        }
    }
}
