package jasmine.framework.cache;

import jasmine.core.util.QCheckUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author mh.z
 */
@Service
public class CacheServiceImpl implements CacheService {
    private RedisTemplate redisTemplate;

    public CacheServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Object get(String category, String key) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        String cacheKey = category + ":" + key;
        Object value = redisTemplate.opsForValue().get(cacheKey);

        return value;
    }

    @Override
    public void put(String category, String key, Object value) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");

        String cacheKey = category + ":" + key;
        redisTemplate.opsForValue().set(cacheKey, value);
    }

}
