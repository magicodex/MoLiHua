package jasmine.framework.cache.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * @author mh.z
 */
public class DefaultRedisTemplateInvoker implements RedisTemplateInvoker {
    private RedisTemplate redisTemplate;
    private ValueOperations valueOperations;

    public DefaultRedisTemplateInvoker(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public Object get(Object key) {
        return valueOperations.get(key);
    }

    @Override
    public void set(Object key, Object value) {
        valueOperations.set(key, value);
    }

    @Override
    public Boolean delete(Object key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Boolean expire(Object key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

}
