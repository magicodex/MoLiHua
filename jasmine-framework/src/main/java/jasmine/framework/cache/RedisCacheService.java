package jasmine.framework.cache;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.common.conversion.DeserializationHelper;
import jasmine.framework.common.conversion.SerializationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * <p>
 * 使用 Redis 实现缓存功能。
 * </p>
 *
 * @author mh.z
 */
@Component
public class RedisCacheService implements CacheService {
    private RedisTemplate redisTemplate;
    private ValueOperations valueOperations;

    /** 缓存同步策略 */
    private CacheSyncStrategy syncStrategy;
    /** 序列化帮助类 */
    private SerializationHelper serializationHelper;
    /** 反序列化帮助类 */
    private DeserializationHelper deserializationHelper;

    /** 缓存key的分隔符 */
    private static final String CACHE_KEY_SEPARATOR = ":";
    /** 默认超时时间 */
    private static final long DEFAULT_TIMEOUT = 3600;

    public RedisCacheService(RedisTemplate redisTemplate,
                             @Autowired(required = false) CacheSyncStrategy syncStrategy) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
        this.syncStrategy = syncStrategy;
        this.serializationHelper = new SerializationHelper();
        this.deserializationHelper = new DeserializationHelper();
    }

    @Override
    public <T> T get(String category, Object key, Class<T> type) {
        return get(category, key, null);
    }

    @Override
    public <T> T get(String category, Object key, Class<T> type, Supplier<T> supplier) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        // 获取缓存key
        String cacheKey = getCacheKey(category, key);
        // 获取缓存的值
        Object value = valueOperations.get(cacheKey);

        if (value == null && supplier != null) {
            // 获取值
            value = supplier.get();
            // 缓存值
            set(category, key, value);
        } else {
            // 反序列化成对象
            value = deserializationHelper.deserialize((byte[]) value, type);
        }

        return (T) value;
    }

    @Override
    public void set(String category, Object key, Object value) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        // 序列化成字节
        byte[] bytes = serializationHelper.serialize(value);
        // 获取缓存key
        String cacheKey = getCacheKey(category, key);

        // 缓存数据
        valueOperations.set(cacheKey, bytes);
        // 设置缓存时间
        redisTemplate.expire(cacheKey, DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String category, Object key) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        // 获取缓存key
        String cacheKey = getCacheKey(category, key);
        // 删除缓存
        redisTemplate.delete(cacheKey);
    }

    @Override
    public void sync(String category, Object key, Supplier<Object> supplier) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        if (supplier != null) {
            if (syncStrategy != null) {
                // 同步缓存
                syncStrategy.sync(category, key, supplier);
            } else {
                // 移除缓存
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

        return (category + CACHE_KEY_SEPARATOR + QStringUtil.toString(key));
    }

}
