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
     * @param type
     * @return
     */
    <T> T get(String category, Object key, Class<T> type);

    /**
     * 从缓存中查找数据
     *
     * @param category
     * @param key
     * @param type
     * @param supplier
     * @return
     */
    <T> T get(String category, Object key, Class<T> type, Supplier<Object> supplier);

    /**
     * 添加数据到缓存中
     *
     * @param category
     * @param key
     * @param value
     */
    void set(String category, Object key, Object value);

    /**
     * 从缓存中清除指定的数据
     *
     * @param category
     * @param key
     */
    void remove(String category, Object key);

    /**
     * 同步数据到缓存中
     *
     * @param category
     * @param key
     * @param supplier
     */
    void sync(String category, Object key, Supplier<Object> supplier);
}
