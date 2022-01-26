package jasmine.framework.lock.distributed;

import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
class RedisLockSupportBean implements GlobalLockSupport {
    private RedissonClient redisson;

    public RedisLockSupportBean(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Override
    public DeclaredGlobalLock declareLock(String category, Object key) {
        String redisKey = category + ":" + key;

        return new RedisDeclaredLock(redisson, redisKey);
    }

}
