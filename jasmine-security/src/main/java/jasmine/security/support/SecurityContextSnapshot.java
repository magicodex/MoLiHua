package jasmine.security.support;

import jasmine.core.context.ContextSnapshot;
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
    public void copyToCurrentThread() {
        SecurityContext targetSecurityContext = SecurityContextHolder.getContext();
        targetSecurityContext.setAuthentication(securityContext.getAuthentication());
    }

}
