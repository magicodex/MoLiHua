package jasmine.core.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class QStringUtilTest {

    @Test
    public void testToString() {
        Assert.assertNull(QStringUtil.toString(null));
        Assert.assertEquals("1001", QStringUtil.toString(1001));
        Assert.assertEquals("1001", QStringUtil.toString("1001"));
    }

    @Test
    public void testOrElse() {
        Assert.assertEquals("value2", QStringUtil.orElse(null, "value2"));
        Assert.assertEquals("", QStringUtil.orElse("", "value2"));
        Assert.assertEquals("value1", QStringUtil.orElse("value1", "value2"));
    }

    @Test
    public void testOrEmpty() {
        Assert.assertEquals("", QStringUtil.orEmpty(null));
        Assert.assertEquals("", QStringUtil.orEmpty(""));
        Assert.assertEquals("value1", QStringUtil.orEmpty("value1"));
    }

}
