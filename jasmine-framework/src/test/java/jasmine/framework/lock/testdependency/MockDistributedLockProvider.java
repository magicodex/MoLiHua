package jasmine.framework.lock.testdependency;

import jasmine.framework.lock.distributed.DistributedDeclaredLock;
import jasmine.framework.lock.distributed.DistributedLockProvider;

/**
 * @author mh.z
 */
public class MockDistributedLockProvider implements DistributedLockProvider {

    @Override
    public DistributedDeclaredLock declareLock(String category, Object key) {
        return new MockDistributedDeclaredLock();
    }

}
