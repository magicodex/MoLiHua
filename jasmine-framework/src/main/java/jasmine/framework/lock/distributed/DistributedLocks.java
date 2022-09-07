package jasmine.framework.lock.distributed;

import jasmine.core.util.QCheckUtil;

import javax.annotation.Nonnull;

/**
 * <p>
 * 分布式锁。
 * </p>
 *
 * @author mh.z
 */
public class DistributedLocks {
    private static DistributedLockProvider provider;

    public static void initUtil(DistributedLockProvider provider) {
        DistributedLocks.provider = provider;
    }

    public static DistributedLockProvider getProvider() {
        return provider;
    }

    /**
     * 声明分布式锁
     *
     * @param category
     * @param key
     * @return
     */
    public static DistributedDeclaredLock declare(@Nonnull String category, @Nonnull Object key) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");
        QCheckUtil.notNullProp(provider, "provider null");

        // 获取分布式锁的实现
        DistributedDeclaredLock lock = provider.declareLock(category, key);

        return lock;
    }

    /**
     * 加锁
     *
     * @param category
     * @param key
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> T lock(@Nonnull String category, @Nonnull Object key,
                             @Nonnull DistributedLockCallback callback) {
        DistributedDeclaredLock lock = declare(category, key);

        return lock.lock(callback);
    }

    /**
     * 加锁
     *
     * @param category
     * @param key
     * @param waitTime
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> T lock(@Nonnull String category, @Nonnull Object key,
                             long waitTime, @Nonnull DistributedLockCallback callback) {
        DistributedDeclaredLock lock = declare(category, key);

        return lock.lock(waitTime, callback);
    }

}
