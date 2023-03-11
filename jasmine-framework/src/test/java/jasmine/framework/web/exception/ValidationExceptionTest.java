package jasmine.framework.web.exception;

import jasmine.framework.web.exception.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author mh.z
 */
public class ValidationExceptionTest {

    @Test
    public void test() {
        Collection<ObjectError> errors = Arrays.asList(
                new ObjectError("field1", "error1"),
                new ObjectError("field2", "error2"));

        {
            ValidationException exception = new ValidationException(errors);
            Assert.assertEquals(ValidationException.DEFAULT_ERROR_CODE, exception.getErrorCode());
        }

        {
            ValidationException exception = new ValidationException("test_error", errors);
            Assert.assertEquals("test_error", exception.getErrorCode());
        }
    }

    @Test
    public void testBuildErrorDetail() {
        Collection<ObjectError> errors = Arrays.asList(
                new ObjectError("field1", "error1"),
                new ObjectError("field2", "error2"));

        String actual = ValidationException.buildErrorDetail(errors);
        Assert.assertNotNull(actual);
    }

}
