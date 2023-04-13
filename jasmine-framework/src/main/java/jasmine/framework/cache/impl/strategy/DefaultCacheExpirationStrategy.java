package jasmine.framework.cache.impl.strategy;

import jasmine.framework.common.util.ObjectUtil;
import jasmine.framework.cache.strategy.CacheExpirationStrategy;

import java.util.Collections;
import java.util.Map;

/**
 * @author mh.z
 */
public class DefaultCacheExpirationStrategy implements CacheExpirationStrategy {
    private Map<String, Long> expirations;
    private Long defaultTimeout;
    /** 默认过期时间 */
    private static final long DEFAULT_TIMEOUT = 3600;

    public DefaultCacheExpirationStrategy() {
        this.expirations = Collections.emptyMap();
        this.defaultTimeout = DEFAULT_TIMEOUT;
    }

    public DefaultCacheExpirationStrategy(Map<String, Long> expirations,
                                          Long defaultTimeout) {
        this.expirations = expirations;
        this.defaultTimeout = defaultTimeout;
    }

    @Override
    public long getTimeout(String category) {
        Long timeout = null;

        if (category != null) {
            timeout = expirations.get(category);
        }

        if (timeout == null) {
            timeout = defaultTimeout;
        }

        return ObjectUtil.defaultIfNull(timeout, DEFAULT_TIMEOUT);
    }

}
