package jasmine.security.support;

import jasmine.framework.context.handler.ContextHandler;
import jasmine.framework.context.handler.ContextSnapshot;
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

    @Override
    public void init() {
        SecurityContextHolder.clearContext();
    }

    @Override
    public void clear() {
        SecurityContextHolder.clearContext();
    }

}
