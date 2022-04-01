package jasmine.framework.cache.context;

import jasmine.framework.context.handler.ContextHandler;
import jasmine.framework.context.handler.ContextSnapshot;

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
