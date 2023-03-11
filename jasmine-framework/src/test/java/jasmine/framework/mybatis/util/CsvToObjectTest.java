package jasmine.framework.mybatis.util;

import jasmine.framework.mybatis.util.CsvToObject;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @author mh.z
 */
public class CsvToObjectTest {

    @Test
    public void testToBoolean() {
        CsvToObject csvToObject = new CsvToObject();

        Assert.assertTrue(csvToObject.toBoolean("1"));
        Assert.assertTrue(csvToObject.toBoolean("true"));
        Assert.assertFalse(csvToObject.toBoolean("0"));
        Assert.assertFalse(csvToObject.toBoolean("false"));
    }

    @Test
    public void testToBigDecimal() {
        CsvToObject csvToObject = new CsvToObject();

        Assert.assertEquals(new BigDecimal("0.1"),
                csvToObject.toBigDecimal("0.1"));
    }

    @Test
    public void testToZonedDateTime() {
        CsvToObject csvToObject = new CsvToObject();

        ZonedDateTime actual = csvToObject.toZonedDateTime("2021-12-31 23:59:59");
        Assert.assertNotNull(actual);
    }

}
