package jasmine.framework.lock.impl.redisson;


import jasmine.core.exception.InvalidPropertyException;
import jasmine.core.util.CheckUtil;
import jasmine.core.util.CollectionUtil;
import jasmine.core.util.ErrorUtil;
import jasmine.framework.lock.distributed.DistributedDeclaredLock;
import jasmine.framework.lock.distributed.DistributedLockCallback;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author mh.z
 */
public class RedissonDistributedDeclaredLock implements DistributedDeclaredLock {
    private RedissonClient redisson;
    private List<String> lockKeys;

    /** 默认等待锁的时间 */
    private static final long DEFAULT_WAIT_TIME = 10000L;
    /** 默认释放锁的时间 */
    private static final long DEFAULT_LEASE_TIME = -1;

    public RedissonDistributedDeclaredLock(RedissonClient redisson, List<String> lockKeys) {
        this.redisson = redisson;
        this.lockKeys = lockKeys;
    }

    @Override
    public <T> T lock(DistributedLockCallback callback) {
        return lock(DEFAULT_WAIT_TIME, callback);
    }

    @Override
    public <T> T lock(long waitTime, DistributedLockCallback callback) {
        CheckUtil.notNull(callback, "callback null");

        if (CollectionUtil.isEmpty(lockKeys)) {
            throw new InvalidPropertyException("lockKeys empty", null);
        }

        RLock lock = null;
        boolean lockFlag = false;

        if (lockKeys.size() > 1) {
            // 多个锁的情况
            //
            int lockTotal = lockKeys.size();
            RLock[] locks = new RLock[lockTotal];

            for (int lockIndex = 0; lockIndex < lockTotal; lockIndex++) {
                String lockKey = lockKeys.get(lockIndex);
                locks[lockIndex] = redisson.getLock(lockKey);
            }

            lock = redisson.getMultiLock(locks);
        } else {
            // 单个锁的情况
            //
            String lockKey = lockKeys.get(0);
            lock = redisson.getLock(lockKey);
        }

        try {
            // 尝试获取锁
            lockFlag = lock.tryLock(waitTime, DEFAULT_LEASE_TIME, TimeUnit.MILLISECONDS);
            if (!lockFlag) {
                throw new RuntimeException("lock failed");
            }

            return (T) callback.call();
        } catch (Throwable e) {
            throw ErrorUtil.sneakyError(e);
        } finally {
            // 释放锁
            if (lockFlag) {
                lock.unlock();
            }
        }
    }

}
