package jasmine.framework.common.exception;

import jasmine.framework.testdependency.pojo.Example1;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class DataNotFoundExceptionTest {

    @Test
    public void test() {
        DataNotFoundException exception = new DataNotFoundException("string1 null", null);
        exception.withErrorDetail(Example1.class, 1L, null);

        String expected = String.format("not found data Example1[key=1]");
        Assert.assertEquals(expected, exception.getErrorDetail());
    }

}
