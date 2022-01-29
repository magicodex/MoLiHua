package jasmine.framework.lock.distributed;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * @author mh.z
 */
public class RedisDeclaredGlobalLockTest {

    @Test
    public void test() throws InterruptedException {
        // 成功获取锁
        {
            RedissonClient client = mockRedissonClient(true);
            RedisDeclaredGlobalLock lock = new RedisDeclaredGlobalLock(client, "testLock");
            boolean actual = lock.lock(() -> true);

            Assert.assertTrue(actual);
        }

        // 获取不到锁会报错
        {
            RedissonClient client = mockRedissonClient(false);
            RedisDeclaredGlobalLock lock = new RedisDeclaredGlobalLock(client, "testLock");

            Assert.assertThrows(RuntimeException.class, () -> {
                lock.lock(() -> true);
            });
        }
    }

    /**
     * 创建 RedissonClient 对象
     *
     * @param tryLockResult
     * @return
     */
    protected RedissonClient mockRedissonClient(boolean tryLockResult) {
        try {
            // 模拟 RLock 对象
            RLock lock = Mockito.mock(RLock.class);
            Mockito.when(lock.tryLock(Mockito.anyLong(), Mockito.anyLong(), Mockito.any()))
                    .thenReturn(tryLockResult);
            // 模拟 RedissonClient 对象
            RedissonClient redisson = Mockito.mock(RedissonClient.class);
            Mockito.when(redisson.getLock(Mockito.any())).thenReturn(lock);

            return redisson;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
