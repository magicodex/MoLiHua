package jasmine.common.global.lock;

import jasmine.common.util.spring.SpringUtil;

/**
 * @author mh.z
 */
public class GlobalLockHelper {

    /**
     * 声明分布式锁
     *
     * @param category
     * @param key
     * @return
     */
    public static DeclaredGlobalLock declareLock(String category, Object key) {
        GlobalLockSupport support = SpringUtil.getBean(GlobalLockSupport.class);

        return support.declareLock(category, key);
    }

}
