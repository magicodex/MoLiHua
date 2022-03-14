package jasmine.framework.mock.testconfigure.middleware;

import jasmine.framework.cache.CacheService;
import jasmine.framework.cache.CacheUtil;
import jasmine.framework.lock.distributed.DistributedLockProvider;
import jasmine.framework.lock.distributed.DistributedLocks;
import jasmine.framework.mock.cache.MockCacheService;
import jasmine.framework.mock.lock.MockDistributedLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class CacheTestConfiguration {

    @Bean
    public CacheService cacheService() {
        CacheService cacheService = new MockCacheService();
        // 初始工具类
        CacheUtil.initUtil(cacheService);

        return cacheService;
    }

    @Bean
    public DistributedLockProvider distributedLockProvider() {
        DistributedLockProvider provider = new MockDistributedLockProvider();
        // 初始工具类
        DistributedLocks.initUtil(provider);

        return provider;
    }

}
