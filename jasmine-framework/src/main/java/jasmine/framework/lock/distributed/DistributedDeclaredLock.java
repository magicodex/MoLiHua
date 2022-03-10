package jasmine.framework.lock.distributed;

import javax.annotation.Nonnull;

/**
 * <p>
 * 声明的分布式锁。
 * </p>
 *
 * @author mh.z
 */
public interface DistributedDeclaredLock {

    /**
     * 先加锁后执行
     *
     * @param callback
     */
    <T> T lock(@Nonnull DistributedLockCallback callback);

    /**
     * 先加锁后执行
     *
     * @param waitTime
     * @param callback
     */
    <T> T lock(long waitTime, @Nonnull DistributedLockCallback callback);
}
