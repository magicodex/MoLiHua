package jasmine.framework.context;

import jasmine.framework.concurrent.ContextCopyHandler;
import jasmine.framework.concurrent.ContextSnapshot;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author mh.z
 */
public class RequestContextCopyHandler implements ContextCopyHandler {

    @Override
    public ContextSnapshot copy() {
        RequestContextSnapshot snapshot = new RequestContextSnapshot();

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        snapshot.setRequestAttributes(requestAttributes);

        return snapshot;
    }

}
