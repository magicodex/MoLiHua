package jasmine.framework.cache;

import java.util.function.Supplier;

/**
 * <p>
 * 提供缓存的功能。
 * </p>
 *
 * @author mh.z
 */
public interface CacheService {

    /**
     * 从缓存中查找数据
     *
     * @param category
     * @param key
     * @return
     */
    Object get(String category, String key);

    /**
     * 从缓存中查找数据
     *
     * @param category
     * @param key
     * @param supplier
     * @return
     */
    Object get(String category, String key, Supplier<Object> supplier);

    /**
     * 添加数据到缓存中
     *
     * @param category
     * @param key
     * @param value
     */
    void set(String category, String key, Object value);

    /**
     * 从缓存中清除指定的数据
     *
     * @param category
     * @param key
     */
    void remove(String category, String key);

    /**
     * 同步数据到缓存中
     *
     * @param category
     * @param key
     * @param supplier
     */
    void sync(String category, String key, Supplier<Object> supplier);
}
