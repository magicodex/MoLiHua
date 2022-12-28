package jasmine.framework.web.exception;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class DegradeExceptionTest {

    @Test
    public void test() {
        DegradeException exception = new DegradeException("test", null);

        Assert.assertEquals(DegradeException.DEFAULT_ERROR_CODE, exception.getErrorCode());
    }

}
