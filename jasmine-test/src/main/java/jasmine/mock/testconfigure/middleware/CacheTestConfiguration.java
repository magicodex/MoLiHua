package jasmine.mock.testconfigure.middleware;

import jasmine.framework.cache.CacheService;
import jasmine.framework.cache.CacheUtil;
import jasmine.framework.lock.distributed.DistributedLockProvider;
import jasmine.framework.lock.distributed.DistributedLocks;
import jasmine.mock.cache.TestCacheService;
import jasmine.mock.lock.TestDistributedLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class CacheTestConfiguration {

    @Bean
    public CacheService cacheService() {
        CacheService cacheService = new TestCacheService();
        CacheUtil.initUtil(cacheService);

        return cacheService;
    }

    @Bean
    public DistributedLockProvider distributedLockProvider() {
        DistributedLockProvider provider = new TestDistributedLockProvider();
        DistributedLocks.initUtil(provider);

        return provider;
    }

}
