package jasmine.framework.cache.context;

import jasmine.framework.context.thread.ContextSnapshot;

/**
 * @author mh.z
 */
public class RequestScopeCacheContextSnapshot implements ContextSnapshot {
    private RequestScopeCacheContext context;

    public RequestScopeCacheContextSnapshot(RequestScopeCacheContext context) {
        this.context = context;
    }

    @Override
    public void copyToCurrentThread() {
        RequestScopeCacheContextHolder.setContext(context);
    }

}
