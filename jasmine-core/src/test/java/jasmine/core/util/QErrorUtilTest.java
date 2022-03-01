package jasmine.core.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class QErrorUtilTest {

    @Test
    public void testSneakyThrow() {
        Assert.assertThrows(Exception.class, () -> {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw QErrorUtil.sneakyError(e);
            }
        });
    }

}
