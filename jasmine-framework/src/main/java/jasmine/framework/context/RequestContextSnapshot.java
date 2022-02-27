package jasmine.framework.context;

import jasmine.core.context.handler.ContextSnapshot;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author mh.z
 */
public class RequestContextSnapshot implements ContextSnapshot {
    private RequestAttributes requestAttributes;

    public RequestContextSnapshot(RequestAttributes requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    @Override
    public void copyToCurrentThread() {
        RequestContextHolder.setRequestAttributes(requestAttributes);
    }

}
