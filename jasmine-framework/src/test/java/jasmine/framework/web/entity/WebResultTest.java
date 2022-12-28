package jasmine.framework.web.entity;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author mh.z
 */
public class WebResultTest {

    @Test
    public void test() {
        // 成功情况
        {
            WebResult<String> result = WebResult.success("Hello, test!");

            Assert.assertTrue(result.getSuccess());
            Assert.assertEquals("Hello, test!", result.getData());
        }

        // 出错情况
        {
            WebResult<String> result = WebResult.error("test", "Hello, test!");

            Assert.assertFalse(result.getSuccess());
            Assert.assertEquals("test", result.getErrorCode());
            Assert.assertEquals("Hello, test!", result.getMessage());
        }

        {
            WebResult<String> result = WebResult.error("test", "Hello, test!");
            result.setErrorDetail("test error!");
            result.setData("test data!");

            Assert.assertEquals("test", result.getErrorCode());
            Assert.assertEquals("Hello, test!", result.getMessage());
            Assert.assertEquals("test error!", result.getErrorDetail());
            Assert.assertEquals("test data!", result.getData());
        }
    }

    @Test
    public void testToEntity() {
        // 成功情况
        {
            WebResult<String> result = WebResult.success("Hello, test!");
            ResponseEntity<WebResult<String>> actual = result.toEntity();

            Assert.assertNotNull(actual);
            Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        }

        // 出错情况
        {
            WebResult<String> result = WebResult.error("test", "Hello, test!");
            ResponseEntity<WebResult<String>> actual = result.toEntity();

            Assert.assertNotNull(actual);
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actual.getStatusCode());
        }
    }

    @Test
    public void testToOkEntity() {
        // 成功情况
        {
            WebResult<String> result = WebResult.success("Hello, test!");
            ResponseEntity<WebResult<String>> actual = result.toOkEntity();

            Assert.assertNotNull(actual);
            Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        }

        // 出错情况
        {
            WebResult<String> result = WebResult.error("test", "Hello, test!");
            ResponseEntity<WebResult<String>> actual = result.toOkEntity();

            Assert.assertNotNull(actual);
            Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        }
    }

}
