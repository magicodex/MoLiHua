package jasmine.framework.lock;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.lock.distributed.DistributedDeclaredLock;
import jasmine.framework.lock.distributed.DistributedLockCallback;
import jasmine.framework.lock.distributed.DistributedLockProvider;

import javax.annotation.Nonnull;

/**
 * <p>
 * 分布式锁。
 * </p>
 *
 * @author mh.z
 */
public class DistributedLockUtil {
    private static DistributedLockProvider provider;

    public static void initUtil(DistributedLockProvider provider) {
        DistributedLockUtil.provider = provider;
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
    public static DistributedDeclaredLock declareLock(@Nonnull String category, @Nonnull Object key) {
        CheckUtil.notNull(category, "category null");
        CheckUtil.notNull(key, "key null");
        CheckUtil.notNullProp(provider, "provider null");

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
        DistributedDeclaredLock lock = declareLock(category, key);

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
        DistributedDeclaredLock lock = declareLock(category, key);

        return lock.lock(waitTime, callback);
    }

}
