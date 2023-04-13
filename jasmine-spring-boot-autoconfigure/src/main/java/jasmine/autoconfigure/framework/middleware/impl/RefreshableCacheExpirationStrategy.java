package jasmine.autoconfigure.framework.middleware.impl;

import jasmine.autoconfigure.framework.middleware.CacheProperties;
import jasmine.framework.common.util.ObjectUtil;
import jasmine.framework.cache.strategy.CacheExpirationStrategy;

/**
 * @author mh.z
 */
public class RefreshableCacheExpirationStrategy implements CacheExpirationStrategy {
    private CacheProperties cacheProperties;

    /** 默认过期时间 */
    private static final long DEFAULT_TIMEOUT = 3600;

    public RefreshableCacheExpirationStrategy(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    @Override
    public long getTimeout(String category) {
        Long timeout = null;

        if (category != null) {
            timeout = cacheProperties.getExpirations().get(category);
        }

        if (timeout == null) {
            timeout = cacheProperties.getDefaultTimeout();
        }

        return ObjectUtil.defaultIfNull(timeout, DEFAULT_TIMEOUT);
    }

}
