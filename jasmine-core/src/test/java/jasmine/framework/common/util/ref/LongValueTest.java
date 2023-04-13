package jasmine.framework.common.util.ref;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class LongValueTest {

    @Test
    public void test() {
        {
            LongValue value = new LongValue(10);
            value.add(5);

            Assert.assertEquals(15, value.get());
        }

        {
            LongValue value = new LongValue(10);
            value.subtract(5);

            Assert.assertEquals(5, value.get());
        }
    }

}
