package jasmine.security.support;

import jasmine.framework.context.thread.ContextHandler;
import jasmine.framework.context.thread.ContextSnapshot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author mh.z
 */
public class SecurityContextHandler implements ContextHandler {

    @Override
    public ContextSnapshot copy() {
        Authentication authentication = SecurityContextUtil.getCurrentAuthentication();
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
