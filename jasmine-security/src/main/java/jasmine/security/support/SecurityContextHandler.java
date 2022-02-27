package jasmine.security.support;

import jasmine.core.context.ContextHandler;
import jasmine.core.context.ContextSnapshot;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author mh.z
 */
public class SecurityContextHandler implements ContextHandler {

    @Override
    public ContextSnapshot copy() {
        SecurityContextSnapshot snapshot = new SecurityContextSnapshot(SecurityContextHolder.getContext());

        return snapshot;
    }

}
