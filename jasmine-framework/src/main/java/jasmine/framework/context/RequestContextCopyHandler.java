package jasmine.framework.context;

import jasmine.framework.concurrent.context.ContextCopyHandler;
import jasmine.framework.concurrent.context.ContextSnapshot;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author mh.z
 */
public class RequestContextCopyHandler implements ContextCopyHandler {

    @Override
    public ContextSnapshot copy() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        RequestContextSnapshot snapshot = new RequestContextSnapshot(requestAttributes);

        return snapshot;
    }

}
