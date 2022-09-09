package jasmine.framework.cache.redis;

import java.util.concurrent.TimeUnit;

/**
 * @author mh.z
 */
public interface RedisTemplateInvoker {

    /**
     * 查找数据
     *
     * @param key
     * @return
     */
    Object get(Object key);

    /**
     * 设置数据
     *
     * @param key
     * @param value
     */
    void set(Object key, Object value);

    /**
     * 删除数据
     *
     * @param key
     * @return
     */
    Boolean delete(Object key);

    /**
     * 设置过期时间
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    Boolean expire(Object key, long timeout, TimeUnit unit);
}
