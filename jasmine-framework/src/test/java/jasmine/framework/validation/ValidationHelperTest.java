package jasmine.framework.validation;

import jasmine.framework.web.entity.WebResult;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        Assert.assertEquals("validate failed", result.getMessage());
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
        Assert.assertEquals("validate failed", result.getMessage());
        Assert.assertNotNull(result.getErrorDetail());
    }

}
