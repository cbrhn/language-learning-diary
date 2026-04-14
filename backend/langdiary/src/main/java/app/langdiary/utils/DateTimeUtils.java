package app.langdiary.utils;
import app.langdiary.progress_tracking.TimeFrame;

import java.time.temporal.ChronoUnit;

public class DateTimeUtils {

    public static ChronoUnit getChronoUnitForTimeFrame (TimeFrame timeframe) {
        return switch (timeframe) {
            case DAY -> ChronoUnit.DAYS;
            case WEEK -> ChronoUnit.WEEKS;
            case MONTH -> ChronoUnit.MONTHS;
            case YEAR -> ChronoUnit.YEARS;
            default -> throw new IllegalArgumentException("Unsupported TimeFrame: " + timeframe);
        };
    }
}
