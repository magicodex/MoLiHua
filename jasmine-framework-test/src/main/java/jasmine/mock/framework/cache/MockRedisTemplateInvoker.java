package jasmine.mock.framework.cache;

import jasmine.framework.cache.impl.redis.RedisTemplateInvoker;
import jasmine.framework.common.util.NewUtil;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author mh.z
 */
public class MockRedisTemplateInvoker implements RedisTemplateInvoker {
    private Map<Object, Object> keyValues;

    public MockRedisTemplateInvoker() {
        this.keyValues = NewUtil.map();
    }

    public MockRedisTemplateInvoker(Map<Object, Object> keyValues) {
        this.keyValues = keyValues;
    }

    public Map<Object, Object> getKeyValues() {
        return keyValues;
    }

    public void setKeyValues(Map<Object, Object> keyValues) {
        this.keyValues = keyValues;
    }

    @Override
    public Object get(Object key) {
        return keyValues.get(key);
    }

    @Override
    public void set(Object key, Object value) {
        keyValues.put(key, value);
    }

    @Override
    public Boolean delete(Object key) {
        boolean result = keyValues.containsKey(key);
        keyValues.remove(key);

        return result;
    }

    @Override
    public Boolean expire(Object key, long timeout, TimeUnit unit) {
        return true;
    }

}
