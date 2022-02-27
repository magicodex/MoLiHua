package jasmine.framework.context;

import jasmine.core.context.ContextHandler;
import jasmine.core.context.ContextSnapshot;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author mh.z
 */
public class RequestContextHandler implements ContextHandler {

    @Override
    public ContextSnapshot copy() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        RequestContextSnapshot snapshot = new RequestContextSnapshot(requestAttributes);

        return snapshot;
    }

    @Override
    public void reset() {
        RequestContextHolder.resetRequestAttributes();
    }

}
