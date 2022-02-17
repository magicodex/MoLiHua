package jasmine.framework.lock.distributed;

/**
 * <p>
 * 加锁后调用的逻辑。
 * </p>
 *
 * @author mh.z
 */
public interface DistributedLockCallback<T> {

    /**
     * 调用加锁后的逻辑
     */
    T call() throws Throwable;
}
