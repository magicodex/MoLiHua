package jasmine.framework.common.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class StringUtilTest {

    @Test
    public void testToString() {
        Assert.assertNull(StringUtil.toString(null));
        Assert.assertEquals("1001", StringUtil.toString(1001));
        Assert.assertEquals("1001", StringUtil.toString("1001"));
    }

    @Test
    public void testOrElse() {
        Assert.assertEquals("value2", StringUtil.orElse(null, "value2"));
        Assert.assertEquals("", StringUtil.orElse("", "value2"));
        Assert.assertEquals("value1", StringUtil.orElse("value1", "value2"));
    }

    @Test
    public void testOrEmpty() {
        Assert.assertEquals("", StringUtil.orEmpty(null));
        Assert.assertEquals("", StringUtil.orEmpty(""));
        Assert.assertEquals("value1", StringUtil.orEmpty("value1"));
    }

}
