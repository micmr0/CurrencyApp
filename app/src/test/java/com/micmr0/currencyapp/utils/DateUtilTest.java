package com.micmr0.currencyapp.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtilTest {
    @Test
    public void testGetDateLabel() {
        long epochSeconds = 1609459200; // 1st January 2021 00:00:00 UTC

        String expectedLabel = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") // 2021-01-01 00:00:00
                .withZone(ZoneId.systemDefault())
                .format(Instant.ofEpochSecond(epochSeconds));

        String actualLabel = DateUtil.getDateLabel(epochSeconds);
        assertEquals(expectedLabel, actualLabel);
    }
}