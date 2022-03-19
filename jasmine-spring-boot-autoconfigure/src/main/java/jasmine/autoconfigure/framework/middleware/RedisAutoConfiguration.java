package jasmine.autoconfigure.framework.middleware;

import jasmine.framework.cache.CacheService;
import jasmine.framework.cache.CacheSyncStrategy;
import jasmine.framework.cache.CacheUtil;
import jasmine.framework.cache.redis.RedisCacheService;
import jasmine.framework.lock.annotation.DistributedLockAspectHandler;
import jasmine.framework.lock.distributed.DistributedLockProvider;
import jasmine.framework.lock.distributed.DistributedLocks;
import jasmine.framework.lock.redisson.RedissonDistributedLockProvider;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@ConditionalOnClass(RedisTemplate.class)
@Configuration
public class RedisAutoConfiguration {

    @Bean
    public CacheService cacheService(RedisTemplate redisTemplate,
                                     @Autowired(required = false) CacheSyncStrategy syncStrategy) {
        RedisCacheService cacheService = new RedisCacheService(redisTemplate);
        cacheService.setSyncStrategy(syncStrategy);

        // 初始工具类
        CacheUtil.initUtil(cacheService);

        return cacheService;
    }

    @Bean
    public DistributedLockAspectHandler distributedLockAspectHandler() {
        return new DistributedLockAspectHandler();
    }

    @Bean
    public DistributedLockProvider distributedLockProvider(RedissonClient redissonClient) {
        DistributedLockProvider provider = new RedissonDistributedLockProvider(redissonClient);
        DistributedLocks.initUtil(provider);

        return provider;
    }

}
