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
        Assert.assertEquals("string1 null", exception.getMessage());
    }

}
