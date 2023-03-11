package jasmine.framework.web.cache;

import jasmine.framework.context.thread.ContextHandler;
import jasmine.framework.context.thread.ContextSnapshot;

/**
 * @author mh.z
 */
public class RequestScopeCacheContextHandler implements ContextHandler {

    @Override
    public ContextSnapshot copy() {
        RequestScopeCacheContext context = RequestScopeCacheContextHolder.getContext();
        RequestScopeCacheContext newContext = null;

        if (context != null) {
            newContext = new RequestScopeCacheContext(context);
        }

        return new RequestScopeCacheContextSnapshot(newContext);
    }

    @Override
    public void init() {
        RequestScopeCacheContextHolder.setContext(new RequestScopeCacheContext());
    }

    @Override
    public void clear() {
        RequestScopeCacheContextHolder.removeContext();
    }

}
