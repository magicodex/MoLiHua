package jasmine.core.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class QObjectUtilTest {

    @Test
    public void testParseLong() {
        // 解析字符串
        Assert.assertEquals(Long.valueOf(1), QObjectUtil.parseLong("1"));
        Assert.assertEquals(Long.valueOf(-1), QObjectUtil.parseLong("-1"));

        // 数字型类型
        Assert.assertEquals(Long.valueOf(1), QObjectUtil.parseLong(Long.valueOf(1)));
        Assert.assertEquals(Long.valueOf(-1), QObjectUtil.parseLong(Long.valueOf(-1)));

        // 空字符串
        Assert.assertNull(QObjectUtil.parseLong(""));
    }

    @Test
    public void testParseInteger() {
        // 解析字符串
        Assert.assertEquals(Integer.valueOf(1), QObjectUtil.parseInteger("1"));
        Assert.assertEquals(Integer.valueOf(-1), QObjectUtil.parseInteger("-1"));

        // 数字型类型
        Assert.assertEquals(Integer.valueOf(1), QObjectUtil.parseInteger(Integer.valueOf(1)));
        Assert.assertEquals(Integer.valueOf(-1), QObjectUtil.parseInteger(Integer.valueOf(-1)));

        // 空字符串
        Assert.assertNull(QObjectUtil.parseInteger(""));
    }

    @Test
    public void testParseBoolean() {
        // 解析字符串
        Assert.assertTrue(QObjectUtil.parseBoolean("true"));
        Assert.assertFalse(QObjectUtil.parseBoolean("false"));

        // 布尔值类型
        Assert.assertTrue(QObjectUtil.parseBoolean(true));
        Assert.assertFalse(QObjectUtil.parseBoolean(false));

        // 空字符串
        Assert.assertNull(QObjectUtil.parseBoolean(""));
    }

}
