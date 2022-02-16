package jasmine.framework.cache;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * @author mh.z
 */
@Component
public class CacheUtil {
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
     * @return
     */
    public Object get(String category, Object key) {
        return cacheService.get(category, key);
    }

    /**
     * 从缓存中查找数据
     *
     * @param category
     * @param key
     * @param supplier
     * @return
     */
    public Object get(String category, Object key, Supplier<Object> supplier) {
        return cacheService.get(category, key, supplier);
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
