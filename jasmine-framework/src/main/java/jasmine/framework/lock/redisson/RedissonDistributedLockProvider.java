package jasmine.framework.lock.redisson;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QNewUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.lock.distributed.DistributedDeclaredLock;
import jasmine.framework.lock.distributed.DistributedLockProvider;
import org.redisson.api.RedissonClient;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
public class RedissonDistributedLockProvider implements DistributedLockProvider {
    private RedissonClient redisson;
    /** 锁key的分隔符 */
    private static final String REDIS_KEY_SEPARATOR = ":";
    /** 锁key的前缀 */
    private static final String REDIS_KEY_PREFIX = "LOCK:";

    public RedissonDistributedLockProvider(RedissonClient redisson) {
        this.redisson = redisson;
    }

    @Override
    public DistributedDeclaredLock declareLock(String category, Object key) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        String redisKeyPrefix = REDIS_KEY_PREFIX + category + REDIS_KEY_SEPARATOR;
        List<String> redisKeyList = null;

        if (key instanceof Iterable) {
            // 多个锁的情况
            //
            Iterable iterable = (Iterable) key;
            redisKeyList = QNewUtil.list();
            final Collection<String> finalRedisKeys = redisKeyList;

            iterable.forEach((current) -> {
                String redisKey = redisKeyPrefix + QStringUtil.toString(key);
                finalRedisKeys.add(redisKey);
            });
        } else {
            // 单个锁的情况
            //
            String redisKey = redisKeyPrefix + QStringUtil.toString(key);
            redisKeyList = Collections.singletonList(redisKey);
        }

        DistributedDeclaredLock lock = new RedissonDistributedDeclaredLock(redisson, redisKeyList);
        return lock;
    }

}
