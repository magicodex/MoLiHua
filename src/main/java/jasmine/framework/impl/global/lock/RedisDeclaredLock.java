package jasmine.framework.impl.global.lock;

import jasmine.common.global.lock.DeclaredGlobalLock;
import jasmine.common.global.lock.GlobalLockCallback;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author mh.z
 */
public class RedisDeclaredLock implements DeclaredGlobalLock {
    private RedissonClient redissonClient;
    private String redisKey;

    public RedisDeclaredLock(RedissonClient redissonClient, String redisKey) {
        this.redissonClient = redissonClient;
        this.redisKey = redisKey;
    }

    @Override
    public <T> T lock(GlobalLockCallback callback) {
        RLock lock = redissonClient.getLock(redisKey);
        lock.lock(10, TimeUnit.SECONDS);

        try {
            return (T) callback.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}
