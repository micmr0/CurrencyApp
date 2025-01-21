package com.micmr0.currencyapp.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    public static String getDateLabel(long seconds) {
        return FORMATTER.format(Instant.ofEpochSecond(seconds));
    }
}
