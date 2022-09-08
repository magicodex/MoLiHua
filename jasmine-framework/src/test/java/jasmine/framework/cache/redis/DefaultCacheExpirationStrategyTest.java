package jasmine.framework.cache.redis;

import jasmine.framework.cache.DefaultCacheExpirationStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author mh.z
 */
public class DefaultCacheExpirationStrategyTest {

    @Test
    public void testGetTimeout() {
        Map<String, Long> expirationMap = Map.of(
                "cache1", 30000L,
                "cache2", 60000L);

        DefaultCacheExpirationStrategy strategy = new DefaultCacheExpirationStrategy(
                expirationMap, 10000L);

        Assert.assertEquals(30000L, strategy.getTimeout("cache1"));
        Assert.assertEquals(60000L, strategy.getTimeout("cache2"));
        Assert.assertEquals(10000L, strategy.getTimeout("cache3"));
    }

}
