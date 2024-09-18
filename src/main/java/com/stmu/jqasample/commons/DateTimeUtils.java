package com.stmu.jqasample.commons;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimeUtils {

    private DateTimeUtils() {
        throw new UnsupportedOperationException("Static utility class cannot be instantiated");
    }

    public static OffsetDateTime getLatest30MinuteInterval() {
        var now = OffsetDateTime.now();
        int minute = now.getMinute();
        int minutesToSubtract = minute % 30;
        OffsetDateTime roundedDateTime = now.minusMinutes(minutesToSubtract);

        roundedDateTime = roundedDateTime.truncatedTo(ChronoUnit.MINUTES);

        return roundedDateTime;
    }
}
