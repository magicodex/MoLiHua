package jasmine.framework.common.conversion;

import jasmine.core.util.QStringUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class DeserializationHelperTest {

    @Test
    public void testConvertText() {
        DeserializationHelper helper = new DeserializationHelper();

        // 布尔值
        Assert.assertEquals(true, helper.deserialize(QStringUtil.utf8Bytes("true"), Boolean.class));
        Assert.assertEquals(false, helper.deserialize(QStringUtil.utf8Bytes("false"), Boolean.class));

        // 字符
        Assert.assertEquals(Character.valueOf('t'), helper
                .deserialize(QStringUtil.utf8Bytes("text"), Character.class));
        Assert.assertEquals(null, helper.deserialize(QStringUtil.utf8Bytes(""), Character.class));

        // 长整型
        Assert.assertEquals(Long.valueOf(1), helper.deserialize(QStringUtil.utf8Bytes("1"), Long.class));
    }

}
