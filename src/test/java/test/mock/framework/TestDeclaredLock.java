package test.mock.framework;

import jasmine.common.global.lock.DeclaredGlobalLock;
import jasmine.common.global.lock.GlobalLockCallback;

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
