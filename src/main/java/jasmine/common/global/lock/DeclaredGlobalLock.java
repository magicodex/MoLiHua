package jasmine.common.global.lock;

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
}
