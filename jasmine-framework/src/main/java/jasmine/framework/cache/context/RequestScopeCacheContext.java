package jasmine.framework.cache.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mh.z
 */
public class RequestScopeCacheContext {
    private Map<String, Object> values;

    public RequestScopeCacheContext() {
        this.values = new ConcurrentHashMap<>();
    }

    public RequestScopeCacheContext(Map<String, Object> values) {
        this.values = values;
    }

    public RequestScopeCacheContext(RequestScopeCacheContext context) {
        this.values = new ConcurrentHashMap<>(context.values);
    }

    public Object getCache(String key) {
        return values.get(key);
    }

    public void setCache(String key, Object value) {
        values.put(key, value);
    }

}
