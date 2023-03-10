package jasmine.core.util;

import org.junit.Assert;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author mh.z
 */
public class DateUtilTest {

    @Test
    public void testParseYearSecond() {
        {
            ZonedDateTime dateTime = DateUtil.parseYearSecond("2022-02-04 12:00:00+08:00");

            Assert.assertEquals(2022, dateTime.getYear());
            Assert.assertEquals(2, dateTime.getMonthValue());
            Assert.assertEquals(4, dateTime.getDayOfMonth());
            Assert.assertEquals(12, dateTime.getHour());
            Assert.assertEquals(0, dateTime.getMinute());
            Assert.assertEquals(0, dateTime.getSecond());
        }
    }

    @Test
    public void testParseStartYearDay() {
        {
            ZonedDateTime dateTime = DateUtil.parseStartYearDay("2022-02-04+08:00");

            Assert.assertEquals(2022, dateTime.getYear());
            Assert.assertEquals(2, dateTime.getMonthValue());
            Assert.assertEquals(4, dateTime.getDayOfMonth());
            Assert.assertEquals(0, dateTime.getHour());
            Assert.assertEquals(0, dateTime.getMinute());
            Assert.assertEquals(0, dateTime.getSecond());
        }
    }

    @Test
    public void testParseEndYearDay() {
        {
            ZonedDateTime dateTime = DateUtil.parseEndYearDay("2022-02-04+08:00");

            Assert.assertEquals(2022, dateTime.getYear());
            Assert.assertEquals(2, dateTime.getMonthValue());
            Assert.assertEquals(5, dateTime.getDayOfMonth());
            Assert.assertEquals(0, dateTime.getHour());
            Assert.assertEquals(0, dateTime.getMinute());
            Assert.assertEquals(0, dateTime.getSecond());
        }
    }

    @Test
    public void testFormatYearSecond() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2022, 2, 4,
                12, 30, 59, 0, ZoneId.systemDefault());
        String actual = DateUtil.formatYearSecond(zonedDateTime);

        Assert.assertEquals("2022-02-04 12:30:59", actual);
    }

    @Test
    public void testFormatYearDay() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2022, 2, 4,
                12, 30, 59, 0, ZoneId.systemDefault());
        String actual = DateUtil.formatYearDay(zonedDateTime);

        Assert.assertEquals("2022-02-04", actual);
    }

}
