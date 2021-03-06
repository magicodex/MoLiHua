package jasmine.framework.lock.distributed;

import javax.annotation.Nonnull;

/**
 * @author mh.z
 */
public interface DistributedLockProvider {

    /**
     * 声明分布式锁
     *
     * @param category
     * @param key
     * @return
     */
    DistributedDeclaredLock declareLock(@Nonnull String category, @Nonnull Object key);
}
