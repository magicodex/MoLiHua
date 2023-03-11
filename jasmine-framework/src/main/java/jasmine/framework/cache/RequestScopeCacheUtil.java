package jasmine.framework.cache;

import jasmine.framework.cache.impl.thread.RequestScopeCacheContext;
import jasmine.framework.cache.impl.thread.RequestScopeCacheContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author mh.z
 */
public class RequestScopeCacheUtil {

    /**
     * 返回缓存
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        Object value = null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes == null) {
            RequestScopeCacheContext cacheContext = RequestScopeCacheContextHolder.getContext();

            if (cacheContext != null) {
                value = cacheContext.getCache(key);
            }
        } else {
            value = requestAttributes.getAttribute(key, RequestAttributes.SCOPE_REQUEST);
        }

        return value;
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes == null) {
            RequestScopeCacheContext cacheContext = RequestScopeCacheContextHolder.getContext();
            if (cacheContext == null) {
                throw new RuntimeException("RequestContextHolder.requestAttributes null");
            }

            cacheContext.setCache(key, value);
        } else {
            requestAttributes.setAttribute(key, value, RequestAttributes.SCOPE_REQUEST);
        }
    }

}
