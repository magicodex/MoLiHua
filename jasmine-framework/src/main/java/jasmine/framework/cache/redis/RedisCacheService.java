package jasmine.framework.cache.redis;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.cache.CacheExpirationStrategy;
import jasmine.framework.cache.CacheService;
import jasmine.framework.cache.CacheSyncStrategy;
import jasmine.framework.cache.DefaultCacheExpirationStrategy;
import jasmine.framework.common.util.SimpleConvertUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * <p>
 * 使用 Redis 实现缓存功能。
 * </p>
 *
 * @author mh.z
 */
public class RedisCacheService implements CacheService {
    private RedisTemplateInvoker redisTemplateInvoker;
    /** 缓存同步策略 */
    private CacheSyncStrategy syncStrategy;
    /** 缓存过期策略 */
    private CacheExpirationStrategy expirationStrategy;

    /** 缓存key的分隔符 */
    private static final String CACHE_KEY_SEPARATOR = ":";
    /** 缓存key的前缀 */
    private static final String CACHE_KEY_PREFIX = "CACHE:";

    public RedisCacheService(RedisTemplateInvoker redisTemplateInvoker) {
        this.redisTemplateInvoker = redisTemplateInvoker;
        this.expirationStrategy = new DefaultCacheExpirationStrategy();
        this.syncStrategy = null;
    }

    public CacheSyncStrategy getSyncStrategy() {
        return syncStrategy;
    }

    public void setSyncStrategy(CacheSyncStrategy syncStrategy) {
        this.syncStrategy = syncStrategy;
    }

    public CacheExpirationStrategy getExpirationStrategy() {
        return expirationStrategy;
    }

    public void setExpirationStrategy(CacheExpirationStrategy expirationStrategy) {
        this.expirationStrategy = (expirationStrategy != null)
                ? expirationStrategy
                : (new DefaultCacheExpirationStrategy());
    }

    @Override
    public <T> T get(String category, Object key, Class<T> type) {
        return get(category, key, type, null);
    }

    @Override
    public <T> T get(String category, Object key, Class<T> type, Supplier<T> supplier) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        // 获取缓存key
        String cacheKey = getCacheKey(category, key);
        // 获取缓存的值
        Object value = redisTemplateInvoker.get(cacheKey);

        if (value == null && supplier != null) {
            // 获取值
            value = supplier.get();
            // 缓存值
            set(category, key, value);
        } else {
            // 反序列化成对象
            value = SimpleConvertUtil.deserialize((byte[]) value, type);
        }

        return (T) value;
    }

    @Override
    public <T> List<T> getList(String category, Object key, Class<T> type) {
        return getList(category, key, type, null);
    }

    @Override
    public <T> List<T> getList(String category, Object key, Class<T> type, Supplier<List<T>> supplier) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        // 获取缓存key
        String cacheKey = getCacheKey(category, key);
        // 获取缓存的值
        Object value = redisTemplateInvoker.get(cacheKey);

        if (value == null && supplier != null) {
            // 获取值
            value = supplier.get();
            // 缓存值
            set(category, key, value);
        } else {
            value = SimpleConvertUtil.deserializeToList((byte[]) value, type);
        }

        return (List<T>) value;
    }

    @Override
    public void set(String category, Object key, Object value) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        // 序列化成字节
        byte[] bytes = SimpleConvertUtil.serialize(value);
        // 获取缓存key
        String cacheKey = getCacheKey(category, key);

        // 缓存数据
        redisTemplateInvoker.set(cacheKey, bytes);
        // 设置缓存时间
        long timeout = expirationStrategy.getTimeout(category);
        redisTemplateInvoker.expire(cacheKey, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String category, Object key) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        // 获取缓存key
        String cacheKey = getCacheKey(category, key);
        // 删除缓存
        redisTemplateInvoker.delete(cacheKey);
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

        return (CACHE_KEY_PREFIX + category + CACHE_KEY_SEPARATOR + QStringUtil.toString(key));
    }

}
