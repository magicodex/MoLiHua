package jasmine.core.exception;

import jasmine.core.testdependency.Example1;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class InvalidDataExceptionTest {

    @Test
    public void test() {
        InvalidDataException exception = new InvalidDataException(Example1.class, 1L, "string1 null", null);

        String expected = String.format("jasmine.core.exception.InvalidDataException: " +
                "string1 null (data Example1[key=1] is invalid)");
        Assert.assertEquals(expected, exception.toString());
    }

}
