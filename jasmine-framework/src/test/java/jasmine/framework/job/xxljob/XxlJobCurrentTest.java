package jasmine.framework.job.xxljob;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author mh.z
 */
public class XxlJobCurrentTest {

    @Test
    public void testParseParameters() {
        XxlJobCurrent current = new XxlJobCurrent();
        Map<String, Object> actualMap = current
                .parseParameters("param1:value1,param2:value2");

        Assert.assertEquals(2, actualMap.size());
        Assert.assertEquals("value1", actualMap.get("param1"));
        Assert.assertEquals("value2", actualMap.get("param2"));
    }

}
