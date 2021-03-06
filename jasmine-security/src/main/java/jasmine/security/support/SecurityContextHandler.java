package jasmine.security.support;

import jasmine.framework.context.handler.ContextHandler;
import jasmine.framework.context.handler.ContextSnapshot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author mh.z
 */
public class SecurityContextHandler implements ContextHandler {

    @Override
    public ContextSnapshot copy() {
        Authentication authentication = null;

        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            authentication = context.getAuthentication();
        }

        SecurityContextSnapshot snapshot = new SecurityContextSnapshot(authentication);
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
