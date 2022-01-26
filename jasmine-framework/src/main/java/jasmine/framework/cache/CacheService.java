package jasmine.framework.cache;

/**
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
     * 添加数据到缓存中
     *
     * @param category
     * @param key
     * @param value
     */
    void put(String category, String key, Object value);
}