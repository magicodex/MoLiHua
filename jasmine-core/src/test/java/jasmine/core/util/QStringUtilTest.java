package jasmine.core.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class QStringUtilTest {

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
