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
    public Object get(String category, String key) {
        return null;
    }

    @Override
    public Object get(String category, String key, Supplier<Object> supplier) {
        return null;
    }

    @Override
    public void set(String category, String key, Object value) {
        //
    }

    @Override
    public void remove(String category, String key) {

    }

    @Override
    public void sync(String category, String key, Supplier<Object> supplier) {

    }

}
