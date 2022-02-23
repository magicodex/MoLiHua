package jasmine.framework.cache;

import java.util.List;
import java.util.function.Supplier;

/**
 * <p>
 * 缓存工具类。
 * </p>
 *
 * @author mh.z
 */
public class CacheUtil {
    /** 缓存接口 */
    private static CacheService cacheService;

    public CacheUtil(CacheService cacheService) {
        CacheUtil.cacheService = cacheService;
    }

    public static void initUtil(CacheService cacheService) {
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
    public static <T> T get(String category, Object key, Class<T> type) {
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
    public static <T> T get(String category, Object key, Class<T> type, Supplier<T> supplier) {
        return cacheService.get(category, key, type, supplier);
    }

    /**
     * 从缓存中查找数据
     *
     * @param category
     * @param key
     * @param supplier
     * @param type
     * @return
     */
    public static <T> T get(String category, Object key, Supplier<T> supplier, Class<T> type) {
        return cacheService.get(category, key, type, supplier);
    }

    /**
     * 从缓存中查找数据
     *
     * @param category
     * @param key
     * @param type
     * @return
     */
    public static <T> List<T> getList(String category, Object key, Class<T> type) {
        return cacheService.getList(category, key, type);
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
    public static <T> List<T> getList(String category, Object key, Class<T> type, Supplier<List<T>> supplier) {
        return cacheService.getList(category, key, type, supplier);
    }

    /**
     * 从缓存中查找数据
     *
     * @param category
     * @param key
     * @param supplier
     * @param type
     * @return
     */
    public static <T> List<T> getList(String category, Object key, Supplier<List<T>> supplier, Class<T> type) {
        return cacheService.getList(category, key, type, supplier);
    }

    /**
     * 添加数据到缓存中
     *
     * @param category
     * @param key
     * @param value
     */
    public static void set(String category, Object key, Object value) {
        cacheService.set(category, key, value);
    }

    /**
     * 从缓存中清除指定的数据
     *
     * @param category
     * @param key
     */
    public static void remove(String category, Object key) {
        cacheService.remove(category, key);
    }

    /**
     * 同步数据到缓存中
     *
     * @param category
     * @param key
     * @param supplier
     */
    public static void sync(String category, Object key, Supplier<Object> supplier) {
        cacheService.sync(category, key, supplier);
    }

}
