package jasmine.framework.lock.distributed;

/**
 * @author mh.z
 */
public interface GlobalLockSupport {

    /**
     * 声明分布式锁
     *
     * @param category
     * @param key
     * @return
     */
    DeclaredGlobalLock declareLock(String category, Object key);
}
