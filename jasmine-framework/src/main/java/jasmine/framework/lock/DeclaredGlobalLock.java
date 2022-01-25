package jasmine.framework.lock;

/**
 * <p>
 * 声明的分布式锁。
 * </p>
 *
 * @author mh.z
 */
public interface DeclaredGlobalLock {

    /**
     * 先加锁后执行
     *
     * @param callback
     */
    <T> T lock(GlobalLockCallback callback);

    /**
     * 先加锁后执行
     *
     * @param waitTime
     * @param callback
     */
    <T> T lock(long waitTime, GlobalLockCallback callback);
}
