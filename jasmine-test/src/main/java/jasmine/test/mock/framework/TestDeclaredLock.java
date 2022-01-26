package jasmine.test.mock.framework;

import jasmine.framework.lock.distributed.DeclaredGlobalLock;
import jasmine.framework.lock.distributed.GlobalLockCallback;

/**
 * @author mh.z
 */
public class TestDeclaredLock implements DeclaredGlobalLock {

    @Override
    public <T> T lock(GlobalLockCallback callback) {
        try {
            return (T) callback.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T lock(long waitTime, GlobalLockCallback callback) {
        try {
            return (T) callback.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
