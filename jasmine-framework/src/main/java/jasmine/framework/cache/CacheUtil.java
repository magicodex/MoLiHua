package jasmine.framework.cache;

import jasmine.core.util.QCheckUtil;

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

    /** 缓存接口 NULL 时的错误信息 */
    private static final String CACHE_SERVICE_NULL_MESSAGE;

    static {
        CACHE_SERVICE_NULL_MESSAGE = CacheUtil.class.getSimpleName() + ".cacheService null";
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
        QCheckUtil.notNullProp(cacheService, CACHE_SERVICE_NULL_MESSAGE);

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
        QCheckUtil.notNullProp(cacheService, CACHE_SERVICE_NULL_MESSAGE);

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
        QCheckUtil.notNullProp(cacheService, CACHE_SERVICE_NULL_MESSAGE);

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
        QCheckUtil.notNullProp(cacheService, CACHE_SERVICE_NULL_MESSAGE);

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
        QCheckUtil.notNullProp(cacheService, CACHE_SERVICE_NULL_MESSAGE);

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
        QCheckUtil.notNullProp(cacheService, CACHE_SERVICE_NULL_MESSAGE);

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
        QCheckUtil.notNullProp(cacheService, CACHE_SERVICE_NULL_MESSAGE);

        cacheService.set(category, key, value);
    }

    /**
     * 从缓存中清除指定的数据
     *
     * @param category
     * @param key
     */
    public static void remove(String category, Object key) {
        QCheckUtil.notNullProp(cacheService, CACHE_SERVICE_NULL_MESSAGE);

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
        QCheckUtil.notNullProp(cacheService, CACHE_SERVICE_NULL_MESSAGE);

        cacheService.sync(category, key, supplier);
    }

}
