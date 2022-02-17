package jasmine.framework.cache;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * <p>
 * 缓存工具类。
 * </p>
 *
 * @author mh.z
 */
@Component
public class CacheUtil {
    /** 缓存接口 */
    private static CacheService cacheService;

    public CacheUtil(CacheService cacheService) {
        CacheUtil.cacheService = cacheService;
    }

    public static void setCacheService(CacheService cacheService) {
        CacheUtil.cacheService = cacheService;
    }

    /**
     * 从缓存中查找数据
     *
     * @param category
     * @param key
     * @param type
     * @return
     */
    public <T> T get(String category, Object key, Class<T> type) {
        return cacheService.get(category, key, type);
    }

    /**
     * 从缓存中查找数据
     *
     * @param category
     * @param key
     * @param type
     * @param supplier
     * @return
     */
    public <T> T get(String category, Object key, Class<T> type, Supplier<T> supplier) {
        return cacheService.get(category, key, type, supplier);
    }

    /**
     * 添加数据到缓存中
     *
     * @param category
     * @param key
     * @param value
     */
    public void set(String category, Object key, Object value) {
        cacheService.set(category, key, value);
    }

    /**
     * 从缓存中清除指定的数据
     *
     * @param category
     * @param key
     */
    public void remove(String category, Object key) {
        cacheService.remove(category, key);
    }

    /**
     * 同步数据到缓存中
     *
     * @param category
     * @param key
     * @param supplier
     */
    public void sync(String category, Object key, Supplier<Object> supplier) {
        cacheService.sync(category, key, supplier);
    }

}