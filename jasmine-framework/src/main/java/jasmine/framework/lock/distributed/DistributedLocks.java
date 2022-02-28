package jasmine.framework.lock.distributed;

import jasmine.core.util.QCheckUtil;

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

    /**
     * 声明分布式锁
     *
     * @param category
     * @param key
     * @return
     */
    public static DistributedDeclaredLock declare(String category, Object key) {
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
    public static <T> T lock(String category, Object key, DistributedLockCallback callback) {
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
    public static <T> T lock(String category, Object key, long waitTime, DistributedLockCallback callback) {
        DistributedDeclaredLock lock = declare(category, key);

        return lock.lock(waitTime, callback);
    }

}
