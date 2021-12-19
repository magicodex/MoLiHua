package jasmine.common.global.lock;

/**
 * <p>
 * 加锁后调用的逻辑。
 * </p>
 *
 * @author mh.z
 */
public interface GlobalLockCallback<T> {

    /**
     * 调用加锁后的逻辑
     */
    T call() throws Exception;
}
