package jasmine.mock.lock;

import jasmine.framework.lock.distributed.DistributedDeclaredLock;
import jasmine.framework.lock.distributed.DistributedLockProvider;

/**
 * @author mh.z
 */
public class TestDistributedLockProvider implements DistributedLockProvider {

    @Override
    public DistributedDeclaredLock declareLock(String category, Object key) {
        return new TestDistributedDeclaredLock();
    }

}
