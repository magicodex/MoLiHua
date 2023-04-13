package jasmine.framework.lock.impl.redisson;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.NewUtil;
import jasmine.framework.common.util.StringUtil;
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
        CheckUtil.notNull(category, "category null");
        CheckUtil.notNull(key, "key null");

        String redisKeyPrefix = REDIS_KEY_PREFIX + category + REDIS_KEY_SEPARATOR;
        List<String> redisKeyList = getRedisKeys(redisKeyPrefix, key);

        DistributedDeclaredLock lock = new RedissonDistributedDeclaredLock(redisson, redisKeyList);
        return lock;
    }

    /**
     * 返回 redis 的 key
     *
     * @param redisKeyPrefix
     * @param key
     * @return
     */
    protected List<String> getRedisKeys(String redisKeyPrefix, Object key) {
        CheckUtil.notNull(redisKeyPrefix, "redisKeyPrefix null");
        CheckUtil.notNull(key, "key null");
        List<String> redisKeyList = null;

        if (key instanceof Iterable) {
            // 多个锁的情况
            //
            Iterable iterable = (Iterable) key;
            redisKeyList = NewUtil.list();
            final Collection<String> finalRedisKeys = redisKeyList;

            iterable.forEach((current) -> {
                String redisKey = redisKeyPrefix + StringUtil.toString(current);
                finalRedisKeys.add(redisKey);
            });
        } else {
            // 单个锁的情况
            //
            String redisKey = redisKeyPrefix + StringUtil.toString(key);
            redisKeyList = Collections.singletonList(redisKey);
        }

        return redisKeyList;
    }

}
