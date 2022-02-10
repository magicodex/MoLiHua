package jasmine.test.mock.lock;

import jasmine.framework.lock.distributed.DistributedDeclaredLock;
import jasmine.framework.lock.distributed.DistributedLockProvider;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class TestDistributedLockProvider implements DistributedLockProvider {

    @Override
    public DistributedDeclaredLock declare(String category, Object key) {
        return new TestDistributedDeclaredLock();
    }

}
