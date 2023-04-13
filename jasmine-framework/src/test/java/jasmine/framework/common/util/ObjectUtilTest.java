package jasmine.framework.common.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class ObjectUtilTest {

    @Test
    public void testParseLong() {
        // 解析字符串
        Assert.assertEquals(Long.valueOf(1), ObjectUtil.parseLong("1"));
        Assert.assertEquals(Long.valueOf(-1), ObjectUtil.parseLong("-1"));

        // 数字型类型
        Assert.assertEquals(Long.valueOf(1), ObjectUtil.parseLong(Long.valueOf(1)));
        Assert.assertEquals(Long.valueOf(-1), ObjectUtil.parseLong(Long.valueOf(-1)));

        // 空字符串
        Assert.assertNull(ObjectUtil.parseLong(""));
    }

    @Test
    public void testParseInteger() {
        // 解析字符串
        Assert.assertEquals(Integer.valueOf(1), ObjectUtil.parseInteger("1"));
        Assert.assertEquals(Integer.valueOf(-1), ObjectUtil.parseInteger("-1"));

        // 数字型类型
        Assert.assertEquals(Integer.valueOf(1), ObjectUtil.parseInteger(Integer.valueOf(1)));
        Assert.assertEquals(Integer.valueOf(-1), ObjectUtil.parseInteger(Integer.valueOf(-1)));

        // 空字符串
        Assert.assertNull(ObjectUtil.parseInteger(""));
    }

    @Test
    public void testParseBoolean() {
        // 解析字符串
        Assert.assertTrue(ObjectUtil.parseBoolean("true"));
        Assert.assertFalse(ObjectUtil.parseBoolean("false"));

        // 布尔值类型
        Assert.assertTrue(ObjectUtil.parseBoolean(true));
        Assert.assertFalse(ObjectUtil.parseBoolean(false));

        // 空字符串
        Assert.assertNull(ObjectUtil.parseBoolean(""));
    }

}
