package jasmine.demo.sample.service;

import jasmine.core.util.QErrorUtil;
import jasmine.framework.lock.annotation.DistributedLock;
import org.springframework.stereotype.Service;

/**
 * @author mh.z
 */
@Service
public class SampleService {

    /**
     * 加锁
     *
     * @param lockName
     * @param lockTime
     */
    @DistributedLock(category = "sample", key = "#lockName")
    public void lock(String lockName, Long lockTime) {
        try {
            Thread.sleep(lockTime);
        } catch (InterruptedException e) {
            throw QErrorUtil.sneakyError(e);
        }
    }

}
