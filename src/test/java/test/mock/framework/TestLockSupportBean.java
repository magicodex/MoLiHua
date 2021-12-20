package test.mock.framework;

import jasmine.common.global.lock.DeclaredGlobalLock;
import jasmine.common.global.lock.GlobalLockSupport;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class TestLockSupportBean implements GlobalLockSupport {

    @Override
    public DeclaredGlobalLock declareLock(String category, Object key) {
        return new TestDeclaredLock();
    }

}
