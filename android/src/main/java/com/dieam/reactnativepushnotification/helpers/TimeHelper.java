package com.dieam.reactnativepushnotification.helpers;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalTime;

import java.util.Random;

public class TimeHelper {

    public static DateTime getNextScheduledTime(DateTime currentTime, LocalTime start, LocalTime end, Duration frequency,
                                         Integer jitter) {

        Integer random = new Random().nextInt(jitter);
        Integer addOrSubtract = new Random().nextInt(1) == 0 ? -1 : 1;

        DateTime nextPossible = currentTime.withDurationAdded(frequency, 1)
                .withDurationAdded(Duration.standardMinutes(random), addOrSubtract);

        if (nextPossible.toLocalTime().isAfter(end)) {
            return currentTime.plusDays(1).withTime(start).withDurationAdded(frequency, 1)
                    .withDurationAdded(Duration.standardMinutes(random), addOrSubtract);
        } else {
            return nextPossible;
        }

    }
}
