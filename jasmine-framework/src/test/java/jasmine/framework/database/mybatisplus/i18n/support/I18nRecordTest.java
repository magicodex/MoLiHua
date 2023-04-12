package jasmine.framework.database.mybatisplus.i18n.support;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author mh.z
 */
public class I18nRecordTest {

    @Test
    public void testGetValueAsString() {
        I18nRecord record = new I18nRecord(new HashMap<>() {{
            put("column1", "1");
            put("column2", null);
        }});

        Assert.assertEquals("1", record.getValueAsString("column1"));
        Assert.assertNull(record.getValueAsString("column2"));
    }

    @Test
    public void testGetValueAsInteger() {
        I18nRecord record = new I18nRecord(new HashMap<>() {{
            put("column1", 1);
            put("column2", null);
        }});

        Assert.assertEquals(Integer.valueOf(1), record.getValueAsInteger("column1"));
        Assert.assertNull(record.getValueAsInteger("column2"));
    }

    @Test
    public void testGetValueAsLong() {
        I18nRecord record = new I18nRecord(new HashMap<>() {{
            put("column1", 1);
            put("column2", null);
        }});

        Assert.assertEquals(Long.valueOf(1), record.getValueAsLong("column1"));
        Assert.assertNull(record.getValueAsLong("column2"));
    }

    @Test
    public void testGetValueAsBoolean() {
        I18nRecord record = new I18nRecord(new HashMap<>() {{
            put("column1", 1);
            put("column2", 0);
            put("column3", null);
        }});

        Assert.assertEquals(Boolean.TRUE, record.getValueAsBoolean("column1"));
        Assert.assertEquals(Boolean.FALSE, record.getValueAsBoolean("column2"));
        Assert.assertNull(record.getValueAsBoolean("column3"));
    }

}
