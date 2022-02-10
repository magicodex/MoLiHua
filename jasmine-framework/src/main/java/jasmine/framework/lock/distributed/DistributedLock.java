package jasmine.framework.lock.distributed;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QSpringUtil;

/**
 * <p>
 * 分布式锁。
 * </p>
 *
 * @author mh.z
 */
public class DistributedLock {

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

        // 获取分布式锁的实现
        DistributedLockProvider provider = QSpringUtil.getBean(DistributedLockProvider.class);
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

}
