package jasmine.security.context;

import jasmine.framework.concurrent.ContextCopyHandler;
import jasmine.framework.concurrent.ContextSnapshot;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class SecurityContextCopyHandler implements ContextCopyHandler {

    @Override
    public ContextSnapshot copy() {
        return null;
    }

}
