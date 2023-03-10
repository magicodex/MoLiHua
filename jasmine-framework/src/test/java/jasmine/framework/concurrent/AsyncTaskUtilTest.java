package jasmine.framework.concurrent;

import jasmine.core.util.CollUtil;
import jasmine.core.util.wrapper.ObjectValue;
import jasmine.framework.concurrent.testdependency.MockAsyncTaskProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
public class AsyncTaskUtilTest {
    private AsyncTaskProvider previousProvider;

    @Before
    public void setUp() {
        previousProvider = AsyncTaskUtil.getProvider();
        AsyncTaskUtil.initUtil(null);
    }

    @After
    public void tearDown() {
        AsyncTaskUtil.initUtil(previousProvider);
    }

    @Test
    public void testAsync() {
        AsyncTaskProvider provider = new MockAsyncTaskProvider();
        AsyncTaskUtil.initUtil(provider);

        ObjectValue value = new ObjectValue(null);
        AsyncTaskUtil.async(() -> {
            value.set("task.run called");
        });

        Assert.assertEquals("task.run called", value.get());
    }

    @Test
    public void testAsyncAndGet() {
        AsyncTaskProvider provider = new MockAsyncTaskProvider();
        AsyncTaskUtil.initUtil(provider);

        List<String> actualList = AsyncTaskUtil.asyncAndGet(Collections.singletonList(() -> {
            return "task.call called";
        }));

        Assert.assertNotNull(actualList);
        Assert.assertEquals(1, actualList.size());
        Assert.assertEquals("task.call called", CollUtil.getFirst(actualList));
    }

}
