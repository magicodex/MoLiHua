package jasmine.security.support;

import jasmine.framework.concurrent.ContextCopyHandler;
import jasmine.framework.concurrent.ContextSnapshot;
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
