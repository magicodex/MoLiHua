package jasmine.framework.cache;

import java.util.function.Supplier;

/**
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
    void sync(String category, String key, Supplier<Object> supplier);
}
