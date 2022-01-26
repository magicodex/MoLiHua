package jasmine.framework.lock.distributed;

import jasmine.core.util.QSpringUtil;
import jasmine.framework.lock.distributed.DeclaredGlobalLock;
import jasmine.framework.lock.distributed.GlobalLockSupport;

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
        GlobalLockSupport support = QSpringUtil.getBean(GlobalLockSupport.class);

        return support.declareLock(category, key);
    }

}
