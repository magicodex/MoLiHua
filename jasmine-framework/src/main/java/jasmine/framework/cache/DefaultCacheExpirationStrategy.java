package jasmine.framework.cache;

import java.util.Collections;
import java.util.Map;

/**
 * @author mh.z
 */
public class DefaultCacheExpirationStrategy implements CacheExpirationStrategy {
    private Map<String, Long> timeoutConfig;
    private Long defaultTimeout;
    /** 默认超时时间 */
    private static final long DEFAULT_TIMEOUT = 3600;

    public DefaultCacheExpirationStrategy() {
        this.timeoutConfig = Collections.emptyMap();
        this.defaultTimeout = DEFAULT_TIMEOUT;
    }

    public DefaultCacheExpirationStrategy(Map<String, Long> timeoutConfig,
                                          Long defaultTimeout) {
        this.timeoutConfig = timeoutConfig;
        this.defaultTimeout = defaultTimeout;
    }

    @Override
    public long getTimeout(String category) {
        Long timeout = null;

        if (category != null) {
            timeout = timeoutConfig.get(category);
        }

        if (timeout == null) {
            timeout = defaultTimeout;
        }

        return timeout;
    }

}
