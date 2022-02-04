package jasmine.core.util;

import org.junit.Assert;
import org.junit.Test;

import java.time.ZonedDateTime;

/**
 * @author mh.z
 */
public class QDateUtilTest {

    @Test
    public void testParseYearSecond() {
        {
            ZonedDateTime dateTime = QDateUtil.parseYearSecond("2022-02-04 12:00:00+08:00");

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
            ZonedDateTime dateTime = QDateUtil.parseStartYearDay("2022-02-04+08:00");

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
            ZonedDateTime dateTime = QDateUtil.parseEndYearDay("2022-02-04+08:00");

            Assert.assertEquals(2022, dateTime.getYear());
            Assert.assertEquals(2, dateTime.getMonthValue());
            Assert.assertEquals(5, dateTime.getDayOfMonth());
            Assert.assertEquals(0, dateTime.getHour());
            Assert.assertEquals(0, dateTime.getMinute());
            Assert.assertEquals(0, dateTime.getSecond());
        }
    }

}
