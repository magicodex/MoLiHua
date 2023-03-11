package jasmine.framework.cache.integration;

import jasmine.framework.cache.impl.thread.RequestScopeCacheContext;
import jasmine.framework.cache.impl.thread.RequestScopeCacheContextHolder;
import jasmine.framework.cache.integration.RequestScopeCacheContextHandler;
import jasmine.framework.context.thread.ContextSnapshot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * @author mh.z
 */
public class RequestScopeCacheContextHandlerTest {
    private RequestScopeCacheContext previousContext;

    @Before
    public void setUp() {
        previousContext = RequestScopeCacheContextHolder.getContext();
        RequestScopeCacheContextHolder.removeContext();
    }

    @After
    public void tearDown() {
        RequestScopeCacheContextHolder.setContext(previousContext);
    }

    @Test
    public void testCopy() {
        RequestScopeCacheContext context = new RequestScopeCacheContext(Map
                .of("key1", "value1"));
        RequestScopeCacheContextHolder.setContext(context);

        // 复制当前的上下文
        RequestScopeCacheContextHandler handler = new RequestScopeCacheContextHandler();
        ContextSnapshot snapshot = handler.copy();

        // 重置当前的上下文
        RequestScopeCacheContextHolder.removeContext();
        {
            RequestScopeCacheContext actual = RequestScopeCacheContextHolder.getContext();
            Assert.assertNull(actual);
        }

        // 还原成之前的上下文
        snapshot.copyToCurrentThread();
        {
            RequestScopeCacheContext actual = RequestScopeCacheContextHolder.getContext();

            Assert.assertNotNull(actual);
            Assert.assertEquals("value1", actual.getCache("key1"));
        }
    }

    @Test
    public void testInit() {
        RequestScopeCacheContextHolder.setContext(null);
        Assert.assertNull(RequestScopeCacheContextHolder.getContext());

        RequestScopeCacheContextHandler handler = new RequestScopeCacheContextHandler();
        // 初始上下文
        handler.init();

        Assert.assertNotNull(RequestScopeCacheContextHolder.getContext());
    }

    @Test
    public void testClear() {
        RequestScopeCacheContextHolder.setContext(new RequestScopeCacheContext());
        Assert.assertNotNull(RequestScopeCacheContextHolder.getContext());

        RequestScopeCacheContextHandler handler = new RequestScopeCacheContextHandler();
        // 清除上下文
        handler.clear();

        Assert.assertNull(RequestScopeCacheContextHolder.getContext());
    }

}
