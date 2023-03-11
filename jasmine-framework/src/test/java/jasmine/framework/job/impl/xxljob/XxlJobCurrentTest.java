package jasmine.framework.job.impl.xxljob;

import com.xxl.job.core.context.XxlJobContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * @author mh.z
 */
public class XxlJobCurrentTest {
    private XxlJobContext previousContext;

    @Before
    public void setUp() {
        previousContext = XxlJobContext.getXxlJobContext();
        XxlJobContext.setXxlJobContext(null);
    }

    @After
    public void tearDown() {
        XxlJobContext.setXxlJobContext(previousContext);
    }

    @Test
    public void test() {
        XxlJobContext jobContext = new XxlJobContext(-1L,
                "", null, -1, -1);
        XxlJobContext.setXxlJobContext(jobContext);

        XxlJobCurrent jobCurrent = new XxlJobCurrent();
        jobCurrent.trace("test trace!");
        jobCurrent.setResult(true, "test result!");

        Assert.assertEquals(200, jobContext.getHandleCode());
    }

    @Test
    public void testGetParameter() {
        XxlJobContext jobContext = new XxlJobContext(1L, "param1:value1,param2:value2",
                "", -1, -1);
        XxlJobContext.setXxlJobContext(jobContext);

        XxlJobCurrent current = new XxlJobCurrent();
        Assert.assertEquals("value1", current.getParameter("param1"));
        Assert.assertEquals("value2", current.getParameter("param2"));
    }

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
