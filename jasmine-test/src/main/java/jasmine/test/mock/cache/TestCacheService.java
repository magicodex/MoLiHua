package jasmine.test.mock.cache;

import jasmine.framework.cache.CacheService;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * @author mh.z
 */
@Service
public class TestCacheService implements CacheService {

    @Override
    public <T> T get(String category, Object key, Class<T> type) {
        return null;
    }

    @Override
    public <T> T get(String category, Object key, Class<T> type, Supplier<T> supplier) {
        return (T) supplier.get();
    }

    @Override
    public void set(String category, Object key, Object value) {
        //
    }

    @Override
    public void remove(String category, Object key) {

    }

    @Override
    public void sync(String category, Object key, Supplier<Object> supplier) {

    }

}
