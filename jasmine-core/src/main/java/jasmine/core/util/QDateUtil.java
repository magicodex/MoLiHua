package jasmine.core.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

/**
 * 日期工具类
 * @author mh.z
 */
public class QDateUtil {
    /** 精确到天的日期格式 */
    private static final DateTimeFormatter YEAR_DAY_FORMATTER_WITH_ZONE;
    /** 精确到秒的日期格式 */
    private static final DateTimeFormatter YEAR_SECOND_FORMATTER_WITH_ZONE;

    static {
        YEAR_DAY_FORMATTER_WITH_ZONE = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .appendOffsetId()
                .toFormatter();

        YEAR_SECOND_FORMATTER_WITH_ZONE = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .appendOffsetId()
                .toFormatter();
    }

    /**
     * 解析成日期，格式是yyyy-MM-dd HH:mm:ssZ
     *
     * @param text
     * @return
     */
    public static ZonedDateTime parseYearSecond(String text) {
        ZonedDateTime zonedDateTime = null;

        if (QStringUtil.isNotEmpty(text)) {
            zonedDateTime = ZonedDateTime.parse(text, YEAR_SECOND_FORMATTER_WITH_ZONE);
        }

        return zonedDateTime;
    }

    /**
     * 解析成开始日期，格式是yyyy-MM-ddZ
     *
     * @param text
     * @return
     */
    public static ZonedDateTime parseStartYearDay(String text) {
        ZonedDateTime zonedDateTime = null;

        if (QStringUtil.isNotEmpty(text)) {
            TemporalAccessor temporalAccessor = YEAR_DAY_FORMATTER_WITH_ZONE.parse(text);
            LocalDate localDate = LocalDate.from(temporalAccessor);

            int offsetSecond = temporalAccessor.get(ChronoField.OFFSET_SECONDS);
            ZoneId zoneId = ZoneOffset.ofTotalSeconds(offsetSecond);
            zonedDateTime = localDate.atStartOfDay(zoneId);
        }

        return zonedDateTime;
    }

    /**
     * 解析成结束日期，格式是yyyy-MM-ddZ
     *
     * @param text
     * @return
     */
    public static ZonedDateTime parseEndYearDay(String text) {
        ZonedDateTime zonedDateTime = null;

        if (QStringUtil.isNotEmpty(text)) {
            zonedDateTime = parseStartYearDay(text);
            zonedDateTime = zonedDateTime.plusDays(1);
        }

        return zonedDateTime;
    }

}
