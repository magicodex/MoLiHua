package jasmine.framework.lock.distributed;

import jasmine.core.util.QCheckUtil;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
class RedisGlobalLockProviderBean implements GlobalLockProvider {
    private RedissonClient redisson;

    public RedisGlobalLockProviderBean(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Override
    public DeclaredGlobalLock declareLock(String category, Object key) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        String redisKey = category + ":" + key;
        DeclaredGlobalLock lock = new RedisDeclaredGlobalLock(redisson, redisKey);

        return lock;
    }

}
