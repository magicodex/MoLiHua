package jasmine.framework.cache.strategy;

import java.util.function.Supplier;

/**
 * <p>
 * 缓存同步策略。
 * </p>
 *
 * @author mh.z
 */
public interface CacheSyncStrategy {

    /**
     * 同步
     *
     * @param category
     * @param key
     * @param supplier
     */
    void sync(String category, Object key, Supplier<Object> supplier);
}
