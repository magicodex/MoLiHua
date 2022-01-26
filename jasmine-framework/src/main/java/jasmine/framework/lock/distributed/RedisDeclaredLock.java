package jasmine.framework.lock.distributed;


import jasmine.core.util.QCheckUtil;
import jasmine.framework.lock.distributed.DeclaredGlobalLock;
import jasmine.framework.lock.distributed.GlobalLockCallback;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author mh.z
 */
public class RedisDeclaredLock implements DeclaredGlobalLock {
    private RedissonClient redisson;
    private String lockKey;

    /** 默认等待锁的时间 */
    private static final long DEFAULT_WAIT_TIME = 5000L;
    /** 默认释放锁的时间 */
    private static final long DEFAULT_LEASE_TIME = 10000L;

    public RedisDeclaredLock(RedissonClient redisson, String lockKey) {
        this.redisson = redisson;
        this.lockKey = lockKey;
    }

    @Override
    public <T> T lock(GlobalLockCallback callback) {
        return lock(DEFAULT_WAIT_TIME, callback);
    }

    @Override
    public <T> T lock(long waitTime, GlobalLockCallback callback) {
        QCheckUtil.notNull(callback, "callback null");

        RLock lock = redisson.getLock(lockKey);
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
