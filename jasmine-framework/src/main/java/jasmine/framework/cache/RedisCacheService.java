package jasmine.framework.cache;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author mh.z
 */
@Service
public class RedisCacheService implements CacheService {
    private RedisTemplate redisTemplate;
    private CacheSyncStrategy syncStrategy;

    /** 缓存key的分隔符 */
    private static final String CATEGORY_KEY_SEPARATOR = ":";
    /** 默认超时时间 */
    private static final long DEFAULT_TIMEOUT = 3600;

    public RedisCacheService(RedisTemplate redisTemplate,
                             @Autowired(required = false) CacheSyncStrategy syncStrategy) {
        this.redisTemplate = redisTemplate;
        this.syncStrategy = syncStrategy;
    }

    @Override
    public Object get(String category, Object key) {
        return get(category, key, null);
    }

    @Override
    public Object get(String category, Object key, Supplier<Object> supplier) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        String cacheKey = getCacheKey(category, key);
        Object value = redisTemplate.opsForValue().get(cacheKey);

        if (value == null && supplier != null) {
            value = supplier.get();
            set(category, key, value);
        }

        return value;
    }

    @Override
    public void set(String category, Object key, Object value) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        String cacheKey = getCacheKey(category, key);
        redisTemplate.opsForValue().set(cacheKey, value);
        redisTemplate.expire(cacheKey, DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String category, Object key) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        String cacheKey = getCacheKey(category, key);
        redisTemplate.delete(cacheKey);
    }

    @Override
    public void sync(String category, Object key, Supplier<Object> supplier) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        if (supplier != null) {
            if (syncStrategy != null) {
                syncStrategy.sync(category, key, supplier);
            } else {
                remove(category, key);
            }
        }
    }

    /**
     * 返回缓存key
     *
     * @param category
     * @param key
     * @return
     */
    protected String getCacheKey(String category, Object key) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        return (category + CATEGORY_KEY_SEPARATOR + QStringUtil.toString(key));
    }

}
