package jasmine.test.mock.framework;

import jasmine.framework.lock.distributed.DeclaredGlobalLock;
import jasmine.framework.lock.distributed.GlobalLockSupport;
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
