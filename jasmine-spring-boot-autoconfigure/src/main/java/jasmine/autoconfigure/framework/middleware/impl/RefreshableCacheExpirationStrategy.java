package jasmine.autoconfigure.framework.middleware.impl;

import jasmine.autoconfigure.framework.middleware.CacheProperties;
import jasmine.framework.cache.CacheExpirationStrategy;

/**
 * @author mh.z
 */
public class RefreshableCacheExpirationStrategy implements CacheExpirationStrategy {
    private CacheProperties cacheProperties;

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

        return timeout;
    }

}
