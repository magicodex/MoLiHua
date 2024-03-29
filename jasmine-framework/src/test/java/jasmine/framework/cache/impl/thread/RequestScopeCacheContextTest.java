package jasmine.framework.cache.impl.thread;

import jasmine.framework.cache.impl.thread.RequestScopeCacheContext;
import org.junit.Assert;

/**
 * @author mh.z
 */
public class RequestScopeCacheContextTest {

    public void test() {
        RequestScopeCacheContext context = new RequestScopeCacheContext();
        context.setCache("key1", "value1");

        Assert.assertEquals("value1", context.getCache("key1"));
        Assert.assertNull(context.getCache("key2"));
    }

}
