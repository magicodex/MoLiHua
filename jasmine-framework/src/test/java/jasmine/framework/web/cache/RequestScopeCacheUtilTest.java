package jasmine.framework.web.cache;

import jasmine.framework.web.cache.RequestScopeCacheContext;
import jasmine.framework.web.cache.RequestScopeCacheContextHolder;
import jasmine.framework.context.thread.web.SimpleRequestAttributes;
import jasmine.framework.web.cache.RequestScopeCacheUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author mh.z
 */
public class RequestScopeCacheUtilTest {
    private RequestAttributes prevRequestAttributes;
    private RequestScopeCacheContext prevRequestScopeCacheContext;

    @Before
    public void setUp() {
        prevRequestAttributes = RequestContextHolder.getRequestAttributes();
        RequestContextHolder.setRequestAttributes(null);

        prevRequestScopeCacheContext = RequestScopeCacheContextHolder.getContext();
        RequestScopeCacheContextHolder.setContext(null);
    }

    @After
    public void tearDown() {
        RequestContextHolder.setRequestAttributes(prevRequestAttributes);
        RequestScopeCacheContextHolder.setContext(prevRequestScopeCacheContext);
    }

    @Test
    public void testGet_1() {
        //
        // RequestContextHolder 和 RequestScopeCacheContextHolder 都初始上下文
        //

        SimpleRequestAttributes requestAttributes = new SimpleRequestAttributes("-1");
        requestAttributes.setAttribute("key1", "value1", RequestAttributes.SCOPE_REQUEST);
        RequestContextHolder.setRequestAttributes(requestAttributes);

        RequestScopeCacheContext cacheContext = new RequestScopeCacheContext();
        cacheContext.setCache("key1", "");
        RequestScopeCacheContextHolder.setContext(cacheContext);

        Object actual = RequestScopeCacheUtil.get("key1");
        Assert.assertEquals("value1", actual);
    }

    @Test
    public void testGet_2() {
        //
        // 只有 RequestScopeCacheContextHolder 初始上下文
        //

        RequestScopeCacheContext cacheContext = new RequestScopeCacheContext();
        cacheContext.setCache("key1", "");
        RequestScopeCacheContextHolder.setContext(cacheContext);

        Object actual = RequestScopeCacheUtil.get("key1");
        Assert.assertEquals("", actual);
    }

    @Test
    public void testGet_3() {
        //
        // RequestContextHolder 和 RequestScopeCacheContextHolder 都没有初始上下文
        //

        Object actual = RequestScopeCacheUtil.get("key1");
        Assert.assertNull("", actual);
    }

    @Test
    public void testSet_1() {
        //
        // RequestContextHolder 和 RequestScopeCacheContextHolder 都初始上下文
        //

        SimpleRequestAttributes requestAttributes = new SimpleRequestAttributes("-1");
        RequestContextHolder.setRequestAttributes(requestAttributes);

        RequestScopeCacheContext cacheContext = new RequestScopeCacheContext();
        RequestScopeCacheContextHolder.setContext(cacheContext);

        RequestScopeCacheUtil.set("key1", "value1");
        Assert.assertEquals("value1", requestAttributes
                .getAttribute("key1", RequestAttributes.SCOPE_REQUEST));
        Assert.assertNull(cacheContext.getCache("key1"));
    }

    @Test
    public void testSet_2() {
        //
        // 只有 RequestScopeCacheContextHolder 初始上下文
        //

        RequestScopeCacheContext cacheContext = new RequestScopeCacheContext();
        RequestScopeCacheContextHolder.setContext(cacheContext);

        RequestScopeCacheUtil.set("key1", "value1");
        Assert.assertEquals("value1", cacheContext.getCache("key1"));
    }

    @Test
    public void testSet_3() {
        //
        // RequestContextHolder 和 RequestScopeCacheContextHolder 都没有初始上下文
        //

        Assert.assertThrows(Exception.class, () -> {
            RequestScopeCacheUtil.set("key1", "value1");
        });
    }

}
