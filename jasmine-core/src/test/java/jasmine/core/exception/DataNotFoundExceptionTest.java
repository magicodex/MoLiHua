package jasmine.core.exception;

import jasmine.core.testdependency.Example1;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class DataNotFoundExceptionTest {

    @Test
    public void test() {
        DataNotFoundException exception = new DataNotFoundException(Example1.class, 1L, "string1 null");

        String expected = String.format("jasmine.core.exception.DataNotFoundException: " +
                "string1 null (not found data Example1[key=1])");
        Assert.assertEquals(expected, exception.toString());
    }

}
