package jasmine.framework.web.util.validation;

import jasmine.framework.testdependency.context.FrameworkTestContext;
import jasmine.framework.web.entity.WebResult;
import jasmine.framework.web.exception.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class ValidationHelperTest extends FrameworkTestContext {

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

    @Test
    public void testToEntity() {
        ValidationHelper helper = ValidationHelper.create();
        helper.field("param1", "").rejectIfEmpty();

        ResponseEntity<WebResult<Object>> actual = helper.toEntity();
        Assert.assertNotNull(actual);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actual.getStatusCode());

        WebResult result = actual.getBody();
        Assert.assertNotNull(result);
        Assert.assertEquals(ValidationException.DEFAULT_ERROR_CODE, result.getErrorCode());
        Assert.assertEquals("校验出错", result.getMessage());
        Assert.assertNotNull(result.getErrorDetail());
    }

    @Test
    public void testToOkEntity() {
        ValidationHelper helper = ValidationHelper.create();
        helper.field("param1", "").rejectIfEmpty();

        ResponseEntity<WebResult<Object>> actual = helper.toOkEntity();
        Assert.assertNotNull(actual);
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());

        WebResult result = actual.getBody();
        Assert.assertNotNull(result);
        Assert.assertEquals(ValidationException.DEFAULT_ERROR_CODE, result.getErrorCode());
        Assert.assertEquals("校验出错", result.getMessage());
        Assert.assertNotNull(result.getErrorDetail());
    }

}
