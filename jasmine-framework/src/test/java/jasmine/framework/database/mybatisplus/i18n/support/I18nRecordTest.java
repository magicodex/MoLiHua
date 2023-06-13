package jasmine.framework.database.mybatisplus.i18n.support;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mh.z
 */
public class I18nRecordTest {

    @Test
    public void testGetValueAsString() {
        Map<String, Object> map = new HashMap();
        map.put("column1", "1");
        map.put("column2", null);
        I18nRecord record = new I18nRecord(map);

        Assert.assertEquals("1", record.getValueAsString("column1"));
        Assert.assertNull(record.getValueAsString("column2"));
    }

    @Test
    public void testGetValueAsInteger() {
        Map<String, Object> map = new HashMap<>();
        map.put("column1", 1);
        map.put("column2", null);
        I18nRecord record = new I18nRecord(map);

        Assert.assertEquals(Integer.valueOf(1), record.getValueAsInteger("column1"));
        Assert.assertNull(record.getValueAsInteger("column2"));
    }

    @Test
    public void testGetValueAsLong() {
        Map<String, Object> map = new HashMap<>();
        map.put("column1", 1);
        map.put("column2", null);
        I18nRecord record = new I18nRecord(map);

        Assert.assertEquals(Long.valueOf(1), record.getValueAsLong("column1"));
        Assert.assertNull(record.getValueAsLong("column2"));
    }

    @Test
    public void testGetValueAsBoolean() {
        Map<String, Object> map = new HashMap<>();
        map.put("column1", 1);
        map.put("column2", 0);
        map.put("column3", null);
        I18nRecord record = new I18nRecord(map);

        Assert.assertEquals(Boolean.TRUE, record.getValueAsBoolean("column1"));
        Assert.assertEquals(Boolean.FALSE, record.getValueAsBoolean("column2"));
        Assert.assertNull(record.getValueAsBoolean("column3"));
    }

}
