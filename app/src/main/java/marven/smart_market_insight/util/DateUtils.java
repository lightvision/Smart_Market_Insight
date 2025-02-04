package marven.smart_market_insight.util;

import android.content.Context;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import marven.smart_market_insight.R;

public class DateUtils {

    private static final DateTimeFormatter[] DATE_FORMATTERS = new DateTimeFormatter[]{
            DateTimeFormatter.RFC_1123_DATE_TIME,
            DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
    };

    public static String getRelativeTime(String pubDate, Context context) {
        try {
            LocalDateTime parsedDateTime = null;
            for (DateTimeFormatter formatter : DATE_FORMATTERS) {
                try {
                    parsedDateTime = LocalDateTime.parse(pubDate, formatter);
                    break;
                } catch (Exception ignored) {
                }
            }
            if (parsedDateTime == null) {
                return pubDate;
            }

            Instant parsedInstant = parsedDateTime.atZone(ZoneId.systemDefault()).toInstant();
            Instant now = Instant.now();
            Duration duration = Duration.between(parsedInstant, now);

            long seconds = duration.getSeconds();
            if (seconds < 60) {
                return context.getString(R.string.now);
            } else if (seconds < 120) {
                return context.getString(R.string.minute_ago);
            } else if (seconds < 3600) {
                return context.getString(R.string.minutes_ago, seconds / 60);
            } else if (seconds < 7200) {
                return context.getString(R.string.hour_ago);
            } else if (seconds < 86400) {
                return context.getString(R.string.hours_ago, seconds / 3600);
            } else if (seconds < 172800) {
                return context.getString(R.string.day_ago);
            } else {
                return context.getString(R.string.days_ago, seconds / 86400);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return pubDate;
        }
    }
}