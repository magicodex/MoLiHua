package jasmine.mock.lock;

import jasmine.framework.lock.distributed.DistributedDeclaredLock;
import jasmine.framework.lock.distributed.DistributedLockCallback;

/**
 * @author mh.z
 */
public class TestDistributedDeclaredLock implements DistributedDeclaredLock {

    @Override
    public <T> T lock(DistributedLockCallback callback) {
        try {
            return (T) callback.call();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T lock(long waitTime, DistributedLockCallback callback) {
        try {
            return (T) callback.call();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
