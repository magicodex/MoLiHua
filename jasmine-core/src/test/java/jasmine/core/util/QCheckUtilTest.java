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
    public void testNotNull() {
        QCheckUtil.notNull(new Object(), "value null");

        Assert.assertThrows(UnexpectedException.class, () -> {
            QCheckUtil.notNull(null, "value null");
        });
    }

    @Test
    public void testNotNullFmt() {
        QCheckUtil.notNullFmt(new Object(), "%s null", "value");

        Assert.assertThrows(UnexpectedException.class, () -> {
            QCheckUtil.notNullFmt(null, "%s null", "value");
        });
    }

    @Test
    public void testNotNullProp() {
        QCheckUtil.notNullProp(new Object(), "value null");

        Assert.assertThrows(InvalidPropertyException.class, () -> {
            QCheckUtil.notNullProp(null, "value null");
        });
    }

    @Test
    public void testNotNullPropFmt() {
        QCheckUtil.notNullPropFmt(new Object(), "%s null", "value");

        Assert.assertThrows(InvalidPropertyException.class, () -> {
            QCheckUtil.notNullPropFmt(null, "%s null", "value");
        });
    }

}
