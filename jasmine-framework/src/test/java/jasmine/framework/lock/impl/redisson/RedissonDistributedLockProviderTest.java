package jasmine.framework.lock.impl.redisson;

import jasmine.framework.common.util.CollectionUtil;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.redisson.api.RedissonClient;

import java.util.Arrays;
import java.util.List;

/**
 * @author mh.z
 */
public class RedissonDistributedLockProviderTest {

    @Test
    public void testGetRedisKeys() {
        RedissonClient redissonClient = mockRedissonClient();
        RedissonDistributedLockProvider provider =
                new RedissonDistributedLockProvider(redissonClient);

        {
            List<String> actualList = provider.getRedisKeys("test:", "lock1");

            Assert.assertNotNull(actualList);
            Assert.assertEquals("test:lock1", CollectionUtil.getFirst(actualList));
        }

        {
            List<String> actualList = provider.getRedisKeys("test:",
                    Arrays.asList("lock1", "lock2", "lock3"));

            Assert.assertNotNull(actualList);
            Assert.assertEquals(3, actualList.size());
            Assert.assertEquals("test:lock1", actualList.get(0));
            Assert.assertEquals("test:lock2", actualList.get(1));
            Assert.assertEquals("test:lock3", actualList.get(2));
        }
    }

    /**
     * 模拟 RedissonClient 对象
     *
     * @return
     */
    protected RedissonClient mockRedissonClient() {
        return Mockito.mock(RedissonClient.class);
    }

}
