package jasmine.security.support;

import jasmine.framework.concurrent.context.ContextSnapshot;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author mh.z
 */
public class SecurityContextSnapshot implements ContextSnapshot {
    private SecurityContext securityContext;

    public SecurityContextSnapshot(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @Override
    public void setToCurrentThread() {
        SecurityContext targetSecurityContext = SecurityContextHolder.getContext();
        targetSecurityContext.setAuthentication(securityContext.getAuthentication());
    }

    @Override
    public void clearFromCurrentThread() {
        SecurityContextHolder.clearContext();
    }

}
