package jasmine.framework.lock.testdependency;

import jasmine.core.util.ErrorUtil;
import jasmine.framework.lock.distributed.DistributedDeclaredLock;
import jasmine.framework.lock.distributed.DistributedLockCallback;

/**
 * @author mh.z
 */
public class MockDistributedDeclaredLock implements DistributedDeclaredLock {

    @Override
    public <T> T lock(DistributedLockCallback callback) {
        try {
            return (T) callback.call();
        } catch (Throwable e) {
            throw ErrorUtil.sneakyError(e);
        }
    }

    @Override
    public <T> T lock(long waitTime, DistributedLockCallback callback) {
        try {
            return (T) callback.call();
        } catch (Throwable e) {
            throw ErrorUtil.sneakyError(e);
        }
    }

}
