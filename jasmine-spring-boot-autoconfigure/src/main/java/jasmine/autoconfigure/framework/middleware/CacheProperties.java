package jasmine.autoconfigure.framework.middleware;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.Map;

/**
 * @author mh.z
 */
@ConfigurationProperties(prefix = "jasmine.cache")
public class CacheProperties {
    /** 默认过期时间 */
    private Long defaultTimeout = 3600L;
    /** 缓存过期时间 */
    private Map<String, Long> expirations = Collections.emptyMap();

    public Long getDefaultTimeout() {
        return defaultTimeout;
    }

    public void setDefaultTimeout(Long defaultTimeout) {
        this.defaultTimeout = defaultTimeout;
    }

    public Map<String, Long> getExpirations() {
        return expirations;
    }

    public void setExpirations(Map<String, Long> expirations) {
        this.expirations = expirations;
    }

}
