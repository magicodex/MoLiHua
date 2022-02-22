package jasmine.autoconfigure.framework.lock;

import jasmine.framework.lock.distributed.DistributedLockProvider;
import jasmine.framework.lock.redis.RedisDistributedLockProvider;
import jasmine.framework.lock.aspect.DistributedLockAspectHandler;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class LockAutoConfiguration {

    @Bean
    public DistributedLockAspectHandler distributedLockAspectHandler() {
        return new DistributedLockAspectHandler();
    }

    @Bean
    public DistributedLockProvider distributedLockProvider(RedissonClient redissonClient) {
        return new RedisDistributedLockProvider(redissonClient);
    }

}
