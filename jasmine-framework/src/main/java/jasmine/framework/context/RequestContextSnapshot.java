package jasmine.framework.context;

import jasmine.framework.concurrent.ContextSnapshot;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author mh.z
 */
public class RequestContextSnapshot implements ContextSnapshot {
    private RequestAttributes requestAttributes;

    public RequestContextSnapshot() {
        //
    }

    public RequestAttributes getRequestAttributes() {
        return requestAttributes;
    }

    public void setRequestAttributes(RequestAttributes requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    @Override
    public void setToCurrentThread() {
        RequestContextHolder.setRequestAttributes(requestAttributes);
    }

    @Override
    public void clearFromCurrentThread() {
        RequestContextHolder.resetRequestAttributes();
    }

}
