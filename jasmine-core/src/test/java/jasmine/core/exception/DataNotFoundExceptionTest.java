package jasmine.core.exception;

import jasmine.core.exception.type.ErrorType;
import jasmine.core.testdependency.Example1;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class DataNotFoundExceptionTest {

    @Test
    public void test() {
        DataNotFoundException exception = new DataNotFoundException(Example1.class, 1L);
        Assert.assertEquals("data not found", exception.getMessage());

        ErrorType errorType = exception.getErrorType();
        Assert.assertNotNull(errorType);
        Assert.assertEquals("not found data Example1[key=1]", errorType.getDetail());
    }

}
