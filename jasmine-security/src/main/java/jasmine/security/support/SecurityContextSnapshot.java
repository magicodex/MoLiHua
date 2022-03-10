package jasmine.security.support;

import jasmine.framework.context.handler.ContextSnapshot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author mh.z
 */
public class SecurityContextSnapshot implements ContextSnapshot {
    private Authentication authentication;

    public SecurityContextSnapshot(Authentication authentication) {
        this.authentication = authentication;
    }

    @Override
    public void copyToCurrentThread() {
        SecurityContext targetSecurityContext = SecurityContextHolder.getContext();
        targetSecurityContext.setAuthentication(authentication);
    }

}
