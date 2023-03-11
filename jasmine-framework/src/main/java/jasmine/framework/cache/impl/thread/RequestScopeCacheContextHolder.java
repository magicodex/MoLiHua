package jasmine.framework.cache.impl.thread;

/**
 * @author mh.z
 */
public class RequestScopeCacheContextHolder {
    private static final ThreadLocal<RequestScopeCacheContext> CONTEXT_LOCAL = new ThreadLocal<>();

    public static RequestScopeCacheContext getContext() {
        return CONTEXT_LOCAL.get();
    }

    public static void setContext(RequestScopeCacheContext context) {
        CONTEXT_LOCAL.set(context);
    }

    public static void removeContext() {
        CONTEXT_LOCAL.remove();
    }

}
