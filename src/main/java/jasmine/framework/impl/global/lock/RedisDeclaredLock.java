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

    private static final long DEFAULT_WAIT_TIME = 5000L;
    private static final long DEFAULT_LEASE_TIME = 10000L;

    public RedisDeclaredLock(RedissonClient redissonClient, String redisKey) {
        this.redissonClient = redissonClient;
        this.redisKey = redisKey;
    }

    @Override
    public <T> T lock(GlobalLockCallback callback) {
        return lock(DEFAULT_WAIT_TIME, callback);
    }

    @Override
    public <T> T lock(long waitTime, GlobalLockCallback callback) {
        RLock lock = redissonClient.getLock(redisKey);
        boolean lockFlag = false;

        try {
            lockFlag = lock.tryLock(waitTime, DEFAULT_LEASE_TIME, TimeUnit.MILLISECONDS);
            if (!lockFlag) {
                throw new RuntimeException("lock failed");
            }

            return (T) callback.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (lockFlag) {
                lock.unlock();
            }
        }
    }

}
