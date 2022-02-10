package jasmine.framework.lock.distributed;

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
    DistributedDeclaredLock declare(String category, Object key);
}
