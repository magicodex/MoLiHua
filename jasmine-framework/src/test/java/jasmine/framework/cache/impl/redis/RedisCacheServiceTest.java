package jasmine.framework.cache.impl.redis;

import jasmine.framework.cache.impl.redis.RedisCacheService;
import jasmine.framework.cache.strategy.CacheSyncStrategy;
import jasmine.framework.cache.testdependency.MockRedisTemplateInvoker;
import jasmine.framework.common.util.SimpleConvertUtil;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

/**
 * @author mh.z
 */
public class RedisCacheServiceTest {
    private static final String KEY_PREFIX = "CACHE:test:";

    @Test
    public void testGet() {
        MockRedisTemplateInvoker invoker = new MockRedisTemplateInvoker();
        invoker.set(KEY_PREFIX + "key1", SimpleConvertUtil.serialize("value1"));
        RedisCacheService service = new RedisCacheService(invoker);

        // 找到指定的缓存
        {
            Object actual = service.get("test", "key1", String.class);
            Assert.assertEquals("value1", actual);
        }

        // 没找到指定的缓存
        {
            Object actual = service.get("test", "key2", String.class);
            Assert.assertNull(actual);
        }
    }

    @Test
    public void testGetList() {
        MockRedisTemplateInvoker invoker = new MockRedisTemplateInvoker();
        invoker.set(KEY_PREFIX + "key1", SimpleConvertUtil.serialize(Arrays.asList("value1")));
        RedisCacheService service = new RedisCacheService(invoker);

        // 找到指定的缓存
        {
            List<String> actualList = service.getList("test", "key1", String.class);
            Assert.assertNotNull(actualList);
            Assert.assertEquals(1, actualList.size());
            Assert.assertEquals("value1", actualList.get(0));
        }

        // 没找到指定的缓存
        {
            List<String> actualList = service.getList("test", "key2", String.class);
            Assert.assertNull(actualList);
        }
    }

    @Test
    public void testSet() {
        MockRedisTemplateInvoker invoker = new MockRedisTemplateInvoker();
        RedisCacheService service = new RedisCacheService(invoker);

        service.set("test", "key1", "value1");
        Object actual = invoker.get(KEY_PREFIX + "key1");

        Assert.assertNotNull(actual);
        Assert.assertEquals("value1", SimpleConvertUtil
                .deserialize((byte[]) actual, String.class));
    }

    @Test
    public void testRemove() {
        MockRedisTemplateInvoker invoker = new MockRedisTemplateInvoker();
        invoker.set(KEY_PREFIX + "key1", SimpleConvertUtil.serialize("value1"));
        invoker.set(KEY_PREFIX + "key2", SimpleConvertUtil.serialize("value2"));
        RedisCacheService service = new RedisCacheService(invoker);

        service.remove("test", "key1");
        Assert.assertNull(invoker.get(KEY_PREFIX + "key1"));
        Assert.assertNotNull(invoker.get(KEY_PREFIX + "key2"));
    }

    @Test
    public void testSync() {
        // 没有指定缓存同步策略
        {
            MockRedisTemplateInvoker invoker = new MockRedisTemplateInvoker();
            invoker.set(KEY_PREFIX + "key1", SimpleConvertUtil.serialize("value1"));
            invoker.set(KEY_PREFIX + "key2", SimpleConvertUtil.serialize("value2"));
            RedisCacheService service = new RedisCacheService(invoker);

            service.sync("test", "key1", () -> "value1");
            Assert.assertNull(invoker.get(KEY_PREFIX + "key1"));
        }

        // 指定缓存同步策略
        {
            MockRedisTemplateInvoker invoker = new MockRedisTemplateInvoker();
            invoker.set(KEY_PREFIX + "key1", SimpleConvertUtil.serialize("value1"));
            invoker.set(KEY_PREFIX + "key2", SimpleConvertUtil.serialize("value2"));
            RedisCacheService service = new RedisCacheService(invoker);
            service.setSyncStrategy(Mockito.mock(CacheSyncStrategy.class));

            service.sync("test", "key1", () -> "value1");
            Assert.assertNotNull(invoker.get(KEY_PREFIX + "key1"));
        }
    }

    @Test
    public void testGetCacheKey() {
        MockRedisTemplateInvoker invoker = new MockRedisTemplateInvoker();
        RedisCacheService service = new RedisCacheService(invoker);

        String actual = service.getCacheKey("test", "key1");
        Assert.assertEquals("CACHE:test:key1", actual);
    }

}
