package jasmine.test.mock.cache;

import jasmine.framework.cache.CacheService;
import org.springframework.stereotype.Service;

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
    public void put(String category, String key, Object value) {
        //
    }

}
