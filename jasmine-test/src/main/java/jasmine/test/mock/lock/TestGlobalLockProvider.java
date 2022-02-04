package jasmine.test.mock.lock;

import jasmine.framework.lock.distributed.DeclaredGlobalLock;
import jasmine.framework.lock.distributed.GlobalLockProvider;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class TestGlobalLockProvider implements GlobalLockProvider {

    @Override
    public DeclaredGlobalLock declareLock(String category, Object key) {
        return new TestDeclaredGlobalLock();
    }

}
