package jasmine.mock.testautoconfigure;

import jasmine.framework.cache.CacheService;
import jasmine.framework.cache.CacheUtil;
import jasmine.framework.lock.distributed.DistributedLockProvider;
import jasmine.framework.lock.distributed.DistributedLocks;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.mock.cache.TestCacheService;
import jasmine.mock.lock.TestDistributedLockProvider;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class MiddleTestConfiguration {

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

    @Bean
    public ReceiveMessageService receiveMessageService() {
        return Mockito.mock(ReceiveMessageService.class);
    }

    @Bean
    public SendMessageService sendMessageService() {
        return Mockito.mock(SendMessageService.class);
    }

}
