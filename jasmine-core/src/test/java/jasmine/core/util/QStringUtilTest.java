package jasmine.core.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class QStringUtilTest {

    @Test
    public void testToString() {
        Assert.assertEquals("string1", QStringUtil.toString("string1"));
    }

    @Test
    public void testOrElse() {
        // 第一个参数不为空
        Assert.assertEquals("string1", QStringUtil.orElse("string1", "string2"));

        // 第一个参数为空
        Assert.assertEquals("string2", QStringUtil.orElse(null, "string2"));
    }

}
