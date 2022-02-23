package jasmine.autoconfigure.framework.cache;

import jasmine.framework.cache.CacheService;
import jasmine.framework.cache.CacheSyncStrategy;
import jasmine.framework.cache.CacheUtil;
import jasmine.framework.cache.redis.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author
 */
@Configuration
public class CacheAutoConfiguration {
    private CacheService cacheService;

    @Bean
    public CacheService cacheService(RedisTemplate redisTemplate,
                                     @Autowired(required = false) CacheSyncStrategy syncStrategy) {
        cacheService = new RedisCacheService(redisTemplate, syncStrategy);

        CacheUtil.setCacheService(cacheService);
        return cacheService;
    }

}
