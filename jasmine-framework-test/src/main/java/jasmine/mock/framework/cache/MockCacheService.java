package jasmine.mock.framework.cache;

import jasmine.framework.cache.CacheService;
import jasmine.framework.common.util.CheckUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author mh.z
 */
@Component
public class MockCacheService implements CacheService {

    @Override
    public <T> T get(String category, Object key, Class<T> type) {
        return null;
    }

    @Override
    public <T> T get(String category, Object key, Class<T> type, Supplier<T> supplier) {
        CheckUtil.notNull(supplier, "supplier null");

        return (T) supplier.get();
    }

    @Override
    public <T> List<T> getList(String category, Object key, Class<T> type) {
        return null;
    }

    @Override
    public <T> List<T> getList(String category, Object key, Class<T> type, Supplier<List<T>> supplier) {
        CheckUtil.notNull(supplier, "supplier null");

        return supplier.get();
    }

    @Override
    public void set(String category, Object key, Object value) {
        //
    }

    @Override
    public void remove(String category, Object key) {
        //
    }

    @Override
    public void sync(String category, Object key, Supplier<Object> supplier) {
        //
    }

}
