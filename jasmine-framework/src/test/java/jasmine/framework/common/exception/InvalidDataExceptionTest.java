package jasmine.framework.common.exception;

import jasmine.framework.testdependency.pojo.Example1;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class InvalidDataExceptionTest {

    @Test
    public void test() {
        InvalidDataException exception = new InvalidDataException("string1 null", null);
        exception.withErrorDetail(Example1.class, 1L, null);

        String expected = String.format("data Example1[key=1] is invalid");
        Assert.assertEquals(expected, exception.getErrorDetail());
    }

}
