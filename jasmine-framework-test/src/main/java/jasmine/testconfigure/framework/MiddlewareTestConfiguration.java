package jasmine.testconfigure.framework;

import jasmine.framework.cache.CacheService;
import jasmine.framework.cache.CacheUtil;
import jasmine.framework.cache.impl.redis.RedisCacheService;
import jasmine.framework.cache.impl.redis.RedisTemplateInvoker;
import jasmine.framework.lock.distributed.DistributedLockProvider;
import jasmine.framework.lock.DistributedLockUtil;
import jasmine.framework.mq.ReceiveMessageService;
import jasmine.framework.mq.SendMessageService;
import jasmine.mock.framework.cache.RedisMockUtil;
import jasmine.mock.framework.lock.MockDistributedLockProvider;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class MiddlewareTestConfiguration {

    @Bean
    public CacheService cacheService() {
        RedisTemplateInvoker redisTemplateInvoker = RedisMockUtil.getRedisTemplateInvoker();
        CacheService cacheService = new RedisCacheService(redisTemplateInvoker);
        // 初始工具类
        CacheUtil.initUtil(cacheService);

        return cacheService;
    }

    @Bean
    public DistributedLockProvider distributedLockProvider() {
        DistributedLockProvider provider = new MockDistributedLockProvider();
        // 初始工具类
        DistributedLockUtil.initUtil(provider);

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
