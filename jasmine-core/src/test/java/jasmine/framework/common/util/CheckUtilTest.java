package jasmine.framework.common.util;

import jasmine.framework.exception.InvalidPropertyException;
import jasmine.framework.exception.UnexpectedException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class CheckUtilTest {

    @Test
    public void testNotNull1() {
        // 校验通过
        CheckUtil.notNull(new Object());

        // 校验不通过报错
        Assert.assertThrows(UnexpectedException.class, () -> {
            CheckUtil.notNull(null);
        });
    }

    @Test
    public void testNotNull2() {
        // 校验通过
        CheckUtil.notNull(new Object(), "value null");

        // 校验不通过报错
        Assert.assertThrows(UnexpectedException.class, () -> {
            CheckUtil.notNull(null, "value null");
        });
    }

    @Test
    public void testNotNullFmt() {
        // 校验通过
        CheckUtil.notNullFmt(new Object(), "%s null", "value");

        // 校验不通过报错
        Assert.assertThrows(UnexpectedException.class, () -> {
            CheckUtil.notNullFmt(null, "%s null", "value");
        });
    }

    @Test
    public void testNotNullProp() {
        // 校验通过
        CheckUtil.notNullProp(new Object(), "value null");

        // 校验不通过报错
        Assert.assertThrows(InvalidPropertyException.class, () -> {
            CheckUtil.notNullProp(null, "value null");
        });
    }

    @Test
    public void testNotNullPropFmt() {
        // 校验通过
        CheckUtil.notNullPropFmt(new Object(), "%s null", "value");

        // 校验不通过报错
        Assert.assertThrows(InvalidPropertyException.class, () -> {
            CheckUtil.notNullPropFmt(null, "%s null", "value");
        });
    }

}
