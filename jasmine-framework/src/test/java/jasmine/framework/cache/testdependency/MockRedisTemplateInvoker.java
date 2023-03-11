package jasmine.framework.cache.testdependency;

import jasmine.framework.cache.impl.redis.RedisTemplateInvoker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author mh.z
 */
public class MockRedisTemplateInvoker implements RedisTemplateInvoker {
    private Map<Object, Object> valueMap;
    private Map<Object, Long> timeoutMap;

    public MockRedisTemplateInvoker() {
        this.valueMap = new HashMap<>();
        this.timeoutMap = new HashMap<>();
    }

    @Override
    public Object get(Object key) {
        return valueMap.get(key);
    }

    @Override
    public void set(Object key, Object value) {
        valueMap.put(key, value);
    }

    @Override
    public Boolean delete(Object key) {
        boolean result = valueMap.containsKey(key);

        valueMap.remove(key);
        timeoutMap.remove(key);

        return result;
    }

    @Override
    public Boolean expire(Object key, long timeout, TimeUnit unit) {
        boolean result = valueMap.containsKey(key);

        if (result) {
            timeoutMap.put(key, timeout);
        }

        return result;
    }

}
