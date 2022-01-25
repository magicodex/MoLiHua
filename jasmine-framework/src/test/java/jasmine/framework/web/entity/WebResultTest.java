package jasmine.framework.web.entity;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class WebResultTest {

    @Test
    public void test() {
        WebResult<String> result = WebResult.success("test data");

        Assert.assertTrue(result.getSuccess());
        Assert.assertEquals("test data", result.getData());
    }

}
