package jasmine.framework.lock.distributed;

import jasmine.core.util.QCheckUtil;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
class RedisDistributedLockProvider implements DistributedLockProvider {
    private RedissonClient redisson;
    /** 锁key的分隔符 */
    private static final String REDIS_KEY_SEPARATOR = ":";
    /** 锁key的前缀 */
    private static final String REDIS_KEY_PREFIX = "LOCK:";

    public RedisDistributedLockProvider(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Override
    public DistributedDeclaredLock declareLock(String category, Object key) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        String redisKey = REDIS_KEY_PREFIX + category + REDIS_KEY_SEPARATOR + key;
        DistributedDeclaredLock lock = new RedisDistributedDeclaredLock(redisson, redisKey);

        return lock;
    }

}
