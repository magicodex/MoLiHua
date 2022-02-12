package jasmine.framework.remote.impl.mq;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class ConvertMessageHelperTest {

    @Test
    public void testConvertText() {
        ConvertMessageHelper helper = new ConvertMessageHelper();

        // 布尔值
        Assert.assertEquals(true, helper.convertText("true", Boolean.class));
        Assert.assertEquals(false, helper.convertText("false", Boolean.class));
        Assert.assertEquals(null, helper.convertText("", Boolean.class));

        // 字符
        Assert.assertEquals('t', helper.convertText("text", Character.class));
        Assert.assertEquals(null, helper.convertText("", Character.class));

        // 长整型
        Assert.assertEquals(Long.valueOf(1), helper.convertText("1", Long.class));
        Assert.assertEquals(null, helper.convertText("", Long.class));
    }

}
