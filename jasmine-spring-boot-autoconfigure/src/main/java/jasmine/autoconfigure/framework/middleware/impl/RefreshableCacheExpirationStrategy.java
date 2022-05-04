package jasmine.autoconfigure.framework.middleware.impl;

import jasmine.autoconfigure.framework.middleware.CacheProperties;
import jasmine.core.util.QObjectUtil;
import jasmine.framework.cache.CacheExpirationStrategy;

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

        return QObjectUtil.defaultIfNull(timeout, DEFAULT_TIMEOUT);
    }

}
