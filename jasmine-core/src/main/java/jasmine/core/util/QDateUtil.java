package jasmine.core.util;

import jasmine.core.constant.DateConstants;

import javax.annotation.Nullable;
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
    private static final DateTimeFormatter YEAR_DAY_FORMATTER;
    /** 精确到秒的日期格式 */
    private static final DateTimeFormatter YEAR_SECOND_FORMATTER_WITH_ZONE;
    private static final DateTimeFormatter YEAR_SECOND_FORMATTER;

    static {
        YEAR_DAY_FORMATTER_WITH_ZONE = new DateTimeFormatterBuilder()
                .appendPattern(DateConstants.YEAR_DAY)
                .appendOffsetId()
                .toFormatter();
        YEAR_DAY_FORMATTER = DateTimeFormatter.ofPattern(DateConstants.YEAR_DAY);

        YEAR_SECOND_FORMATTER_WITH_ZONE = new DateTimeFormatterBuilder()
                .appendPattern(DateConstants.YEAR_SECOND)
                .appendOffsetId()
                .toFormatter();
        YEAR_SECOND_FORMATTER = DateTimeFormatter.ofPattern(DateConstants.YEAR_SECOND);
    }

    /**
     * 解析成日期，格式是yyyy-MM-dd HH:mm:ssZ
     *
     * @param text
     * @return
     */
    public static ZonedDateTime parseYearSecond(@Nullable String text) {
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
    public static ZonedDateTime parseStartYearDay(@Nullable String text) {
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
    public static ZonedDateTime parseEndYearDay(@Nullable String text) {
        ZonedDateTime zonedDateTime = null;

        if (QStringUtil.isNotEmpty(text)) {
            zonedDateTime = parseStartYearDay(text);
            zonedDateTime = zonedDateTime.plusDays(1);
        }

        return zonedDateTime;
    }

    /**
     * 格式化日期成字符串，格式是yyyy-MM-dd HH:mm:ss
     *
     * @param zonedDateTime
     * @return
     */
    public static String formatYearSecond(@Nullable ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) {
            return null;
        }

        return YEAR_SECOND_FORMATTER.format(zonedDateTime);
    }

    /**
     * 格式化日期成字符串，格式是yyyy-MM-dd
     *
     * @param zonedDateTime
     * @return
     */
    public static String formatYearDay(@Nullable ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) {
            return null;
        }

        return YEAR_DAY_FORMATTER.format(zonedDateTime);
    }

}
