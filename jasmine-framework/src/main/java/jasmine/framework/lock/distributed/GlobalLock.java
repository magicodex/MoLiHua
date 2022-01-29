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
public class GlobalLock {

    /**
     * 声明分布式锁
     *
     * @param category
     * @param key
     * @return
     */
    public static DeclaredGlobalLock declareLock(String category, Object key) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        // 获取分布式锁的实现
        GlobalLockProvider provider = QSpringUtil.getBean(GlobalLockProvider.class);
        DeclaredGlobalLock lock = provider.declareLock(category, key);

        return lock;
    }

}
