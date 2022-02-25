package jasmine.security.support;

import jasmine.framework.concurrent.context.ContextCopyHandler;
import jasmine.framework.concurrent.context.ContextSnapshot;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author mh.z
 */
public class SecurityContextCopyHandler implements ContextCopyHandler {

    @Override
    public ContextSnapshot copy() {
        SecurityContextSnapshot snapshot = new SecurityContextSnapshot(SecurityContextHolder.getContext());

        return snapshot;
    }

}
