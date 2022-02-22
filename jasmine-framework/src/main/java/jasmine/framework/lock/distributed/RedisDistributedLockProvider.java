package jasmine.framework.lock.distributed;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QNewUtil;
import jasmine.core.util.QStringUtil;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

        String redisKeyPrefix = REDIS_KEY_PREFIX + category + REDIS_KEY_SEPARATOR;
        List<String> redisKeyList = null;

        if (key instanceof Iterable) {
            Iterable iterable = (Iterable) key;
            redisKeyList = QNewUtil.list();
            final Collection<String> finalRedisKeys = redisKeyList;

            iterable.forEach((current) -> {
                String redisKey = redisKeyPrefix + QStringUtil.toString(key);
                finalRedisKeys.add(redisKey);
            });
        } else {
            String redisKey = redisKeyPrefix + QStringUtil.toString(key);
            redisKeyList = Collections.singletonList(redisKey);
        }

        DistributedDeclaredLock lock = new RedisDistributedDeclaredLock(redisson, redisKeyList);
        return lock;
    }

}
