package jasmine.framework.validation;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class ValidationHelperTest {

    @Test
    public void test() {
        {
            ValidationHelper helper = ValidationHelper.create();
            helper.field("param1", "").rejectIfEmpty();

            Assert.assertTrue(helper.hasErrors());
        }

        {
            ValidationHelper helper = ValidationHelper.create();
            helper.field("param1", "value1").rejectIfEmpty();

            Assert.assertFalse(helper.hasErrors());
        }
    }

}
