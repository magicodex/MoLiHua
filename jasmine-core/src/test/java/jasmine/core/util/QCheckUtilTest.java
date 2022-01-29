package jasmine.core.util;

import jasmine.core.exception.InvalidPropertyException;
import jasmine.core.exception.UnexpectedException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class QCheckUtilTest {

    @Test
    public void testNotNull1() {
        // 校验通过
        QCheckUtil.notNull(new Object());

        // 校验不通过报错
        Assert.assertThrows(UnexpectedException.class, () -> {
            QCheckUtil.notNull(null);
        });
    }

    @Test
    public void testNotNull2() {
        // 校验通过
        QCheckUtil.notNull(new Object(), "value null");

        // 校验不通过报错
        Assert.assertThrows(UnexpectedException.class, () -> {
            QCheckUtil.notNull(null, "value null");
        });
    }

    @Test
    public void testNotNullFmt() {
        // 校验通过
        QCheckUtil.notNullFmt(new Object(), "%s null", "value");

        // 校验不通过报错
        Assert.assertThrows(UnexpectedException.class, () -> {
            QCheckUtil.notNullFmt(null, "%s null", "value");
        });
    }

    @Test
    public void testNotNullProp() {
        // 校验通过
        QCheckUtil.notNullProp(new Object(), "value null");

        // 校验不通过报错
        Assert.assertThrows(InvalidPropertyException.class, () -> {
            QCheckUtil.notNullProp(null, "value null");
        });
    }

    @Test
    public void testNotNullPropFmt() {
        // 校验通过
        QCheckUtil.notNullPropFmt(new Object(), "%s null", "value");

        // 校验不通过报错
        Assert.assertThrows(InvalidPropertyException.class, () -> {
            QCheckUtil.notNullPropFmt(null, "%s null", "value");
        });
    }

}
